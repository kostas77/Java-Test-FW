package com.elsevier.bts.regional.ecom.framework.base;

import lombok.extern.slf4j.Slf4j;

import javax.mail.*;
import javax.mail.internet.MimeMultipart;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
public class EmailAPI {

    public static String subjectMail;

    public static boolean verifyLatestEmailBodyContainsTheExpectedText(String userID, String userPassword, String textToSearchInTheEmailBody) {
        boolean result = false;
        String host = "imap.gmail.com";
        String mailStoreType = "imaps";
        try {
            //create properties field
            Properties properties = new Properties();
            properties.setProperty("mail.store.protocol", "imaps");
            Session emailSession = Session.getDefaultInstance(properties);

            //create the POP3 store object and connect with the pop server
            Store store = emailSession.getStore(mailStoreType);

            store.connect(host, userID, userPassword);

            //create the folder object and open it
            Folder emailFolder = store.getFolder("INBOX");
            emailFolder.open(Folder.READ_WRITE);

            // retrieve the messages from the folder in an array and print it
            Message[] messages = emailFolder.getMessages();

            System.out.println("TOTAL NUMBER OF E-MAILS: " + emailFolder.getMessageCount());

            // Pick up the newest message
            Message newestEmail = messages[messages.length - 1];
            System.out.println("NEWEST E-MAIL SUBJECT: " + newestEmail.getSubject());
            String newestEmailContent = "";
            if (newestEmail.isMimeType("Multipart/*")) {
                MimeMultipart mimeMultipart = (MimeMultipart) newestEmail.getContent();
                newestEmailContent = getTextFromMimeMultipart(mimeMultipart);
            } else {
                newestEmailContent = newestEmail.getContent().toString();
            }
            System.out.println("NEWEST E-MAIL BODY: " + newestEmailContent);
            System.out.println("SEARCHING IN THE E-MAIL BODY FOR: " + textToSearchInTheEmailBody);
            if (newestEmailContent.contains(textToSearchInTheEmailBody)) {
                result = true;
            } else {
                log.debug(" FAILED to the find the EMAIL in the Inbox ");
                result = false;
            }

            //reset the flags of all e-mails to UNSEEN
            emailFolder.setFlags(messages, new Flags(Flags.Flag.SEEN), false);
            //close the store and folder objects
            emailFolder.close(false);
            store.close();
        } catch (NoSuchProviderException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    private static String getTextFromMimeMultipart(MimeMultipart mimeMultipart) throws MessagingException, IOException {
        String result = "";
        int count = mimeMultipart.getCount();
        for (int i = 0; i < count; i++) {
            BodyPart bodyPart = mimeMultipart.getBodyPart(i);
            if (bodyPart.isMimeType("text/plain")) {
                result = result + "\n" + bodyPart.getContent();
                break; // without break same text appears twice in my tests
            } else if (bodyPart.isMimeType("text/html")) {
                String html = (String) bodyPart.getContent();
                result = result + "\n" + org.jsoup.Jsoup.parse(html).text();
            } else if (bodyPart.getContent() instanceof MimeMultipart) {
                result = result + getTextFromMimeMultipart((MimeMultipart) bodyPart.getContent());
            }
        }
        return result;
    }

    public static Optional<Message> findRecentMessage(Folder emailFolder, String subjectKeyword) throws MessagingException {
        int msgIndex = emailFolder.getMessageCount();
        while (msgIndex > 0) {
            try {
                Message msg = emailFolder.getMessage(msgIndex);
                if (msg.getSubject().contains(subjectKeyword)) {
                    return Optional.of(msg);
                }
                msgIndex--;
            } catch (Throwable e) {
                e.printStackTrace();
                // continue - I don't care exception for now
            }
        }
        return Optional.empty();
    }

    public static String retrieveLinkFromEmails(String userID, String userPassword, final String keyword) throws MessagingException {
        String host = "imap.gmail.com";
        String mailStoreType = "imaps";
        String lastRelevantEmailContent = "";
        try {
            //create properties field
            Properties properties = new Properties();
            properties.setProperty("mail.store.protocol", "imaps");
            Session emailSession = Session.getDefaultInstance(properties);

            //create the POP3 store object and connect with the pop server
            Store store = emailSession.getStore(mailStoreType);
            store.connect(host, userID, userPassword);

            //create the folder object and open it
            Folder emailFolder = store.getFolder("INBOX");
            emailFolder.open(Folder.READ_ONLY);

            Optional<Message> messageOpt = findRecentMessage(emailFolder, keyword);
            Message msg = messageOpt.get();
            System.out.println(String.format("Found message #: %s", msg.getSubject()));
            subjectMail=msg.getSubject();
            // Pick up the latest relevant message
            System.out.println("Latest E-MAIL SUBJECT: " + msg.getSubject());
            if (msg.isMimeType("Multipart/*")) {
                MimeMultipart mimeMultipart = (MimeMultipart) msg.getContent();
                lastRelevantEmailContent = getTextFromMimeMultipart(mimeMultipart);
            } else {
                lastRelevantEmailContent = msg.getContent().toString();
            }

            List<String> containedUrls = new ArrayList<String>();
            String urlRegex = "((https?|ftp|gopher|telnet|file):((//)|(\\\\))+[\\w\\d:#@%/;$()~_?\\+-=\\\\\\.&]*)";
            Pattern pattern = Pattern.compile(urlRegex, Pattern.CASE_INSENSITIVE);
            Matcher urlMatcher = pattern.matcher(lastRelevantEmailContent);

            while (urlMatcher.find()) {
                containedUrls.add(lastRelevantEmailContent.substring(urlMatcher.start(0),
                        urlMatcher.end(0)));
            }

//            log.debug("Latest relevant E-MAIL BODY: " + lastRelevantEmailContent);
            //return containedUrls.get(0);

        } catch (IOException e) {
            e.printStackTrace();
        }

        return lastRelevantEmailContent;
    }

    public static TrustManager[] trustAllCerts = new TrustManager[]{
            new X509TrustManager() {
                public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                    return null;
                }

                public void checkClientTrusted(
                        java.security.cert.X509Certificate[] certs, String authType) {
                }

                public void checkServerTrusted(
                        java.security.cert.X509Certificate[] certs, String authType) {
                }
            }};

    public static void handleCertificates() throws NoSuchAlgorithmException, KeyManagementException {
        SSLContext sc = SSLContext.getInstance("TLS");
        sc.init(null, trustAllCerts, new java.security.SecureRandom());
        HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());

        SSLContext ssl = SSLContext.getInstance("SSL");
        ssl.init(null, trustAllCerts, new java.security.SecureRandom());
        HttpsURLConnection.setDefaultSSLSocketFactory(ssl.getSocketFactory());
    }

}

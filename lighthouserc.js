module.exports = {
    ci: {
        collect: {
            url: ['https://www.us.elsevierhealth.com'],
            numberOfRuns: 3,
        },
        // upload: {
        //     target: 'temporary-public-storage',
        // },
        upload: {
            target: 'lhci',
            serverBaseUrl: 'http://54.76.206.129:9001',
            token: '924fb67e-e07d-400b-8a9a-0e918f6d194e', // <-- Your project-specific token here
        },
        "assert": {
            "assertions": {
                "categories:performance": ["error", { "minScore": 0.1 }],
                "categories:accessibility": ["error", { "minScore": 0.1 }],
                "categories:best-practices": ["warn", { "minScore": 1 }],
                "categories:seo": ["error", { "minScore": 0.1 }],
                "categories:pwa": ["warn", { "minScore": 0.99 }]
            }
        }
    },
};

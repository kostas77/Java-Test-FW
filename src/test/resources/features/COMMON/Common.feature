Feature: Common Non-Functional tests

  @perf @lighthouse @ignore
  Scenario: Run the LightHouse plugin on the USHS home page
    Given the user visits the Reviewer Recommender page
    Then a LightHouse report is generated for that page

  @zap_passive @ignore
  Scenario Outline: Run a ZAP passive security scan on the target page - Tardis
    Given the user visits the <TARGET page>
    Then a ZAP passive security scan is performed
    Examples:
      | TARGET page                        |
#      | Reviewer Recommender page          |
      | Manuscript Dashboard page: default |

  @zap_passive @ignore
  Scenario Outline: Run a ZAP passive security scan on the target page - Odin
    Given a user accesses the <TARGET page>
    Then a ZAP passive security scan is performed
    Examples:
      | TARGET page                  |
      | Author Hub                   |
      | Author Hub resubmission page |

  @zap_passive @ignore
  Scenario: Run a ZAP passive security scan on the target page - Engage
    Given a user logs in to the Reviewer Hub
    Then a ZAP passive security scan is performed

  @zap_spider @ignore
  Scenario Outline: Run a ZAP spider security scan on the target page - Tardis
    Given the user visits the <TARGET page>
    Then a ZAP spider security scan is performed
    Examples:
      | TARGET page                        |
#      | Reviewer Recommender page          |
      | Manuscript Dashboard page: default |

  @zap_active @ignore
  Scenario Outline: Run a ZAP active security scan on the target page - Tardis
    Given the user visits the <TARGET page>
    Then a ZAP active security scan is performed
    Examples:
      | TARGET page                        |
#      | Reviewer Recommender page          |
      | Manuscript Dashboard page: default |

  @zap_spider @ignore
  Scenario Outline: Run a ZAP passive security scan on the target page - Odin
    Given the user visits the <TARGET> demo app
    Then a ZAP passive security scan is performed
#    Then a ZAP spider security scan is performed
#    Then a ZAP active security scan is performed
    Examples:
      | TARGET  |
      | Gruyere |
#      | Public Firing Range                   |
#      | Public Firing Range - Address DOM XSS |
#      | Hack.me                               |
#      | bWAPP                                 |

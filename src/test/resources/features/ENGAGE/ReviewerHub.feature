Feature: Reviewer Hub
  All different Reviewer Hub sections are visible and accessible for a logged in user

  @engage_smoke @reviewerhubregression
  Scenario: Verify that all the sections of Reviewer Hub are visible
    Given a user logs in to the Reviewer Hub
     Then the Reviewer Hub Header is displayed
      And each left side panel section is displayed as expected and can be accessed
      | Review Invitations  |
      | Reviews in Progress |
      | Review History      |

  @reviewerhubregression @ignore @wip
  Scenario: Verify users of Reviewer Hub can log out successfully
    Given a user logs in to the Reviewer Hub
     Then the user can logout from the Reviewer Hub

  @engage_smoke @reviewerhubregression
  Scenario: Verify that users of Reviewer Hub can access their peer review invitations
    Given a user logs in to the Reviewer Hub
      And the user has outstanding review invitations
     Then the user is able to view and access their review invitations
#      And the user can view the abstract of their review invitations

  @engage_smoke @reviewerhubregression
  Scenario: Verify that users of Reviewer Hub can access their reviews in progress
    Given a user logs in to the Reviewer Hub
      And the user has some existing reviews in progress
     Then the user is able to view and access their reviews in progress
#      And the user can view the abstract of their reviews in progress

  @engage_smoke @reviewerhubregression
  Scenario: Verify users of Reviewer Hub have completed reviews in multiple journals
    Given a user logs in to the Reviewer Hub
      And the user has completed reviews in different journals
     Then the user is able to view and access their completed reviews in all journals
#      And the user can view the abstract of their completed reviews in all journals

  @engage_smoke @reviewerhubregression
  Scenario: Verify that pagination in the Reviewer Hub app works as expected
    Given a user logs in to the Reviewer Hub
     When they have completed more than 10 reviews for Elsevier journals
     Then their completed reviews are paginated in the Review History section

  @reviewerhub_a11y
  Scenario: Verify that the Reviewer Hub home page complies with A11y requirements
    Given a user logs in to the Reviewer Hub
      And the user has outstanding review invitations
     Then the current page is tested for A11y
      And the user has some existing reviews in progress
     Then the current page is tested for A11y
      And the user has completed reviews in different journals
     Then the current page is tested for A11y

#  @reviewerhub_a11y
#  Scenario: Verify that the Reviewer Hub abstract pane complies with A11y requirements
#    Given a user logs in to the Reviewer Hub
#     When the user has outstanding review invitations
#      And the user views the abstract pane of their outstanding review invitations
#     Then the current page is tested for A11y
#     When the user has some existing reviews in progress
#      And the user views the abstract pane of their reviews in progress
#     Then the current page is tested for A11y
#     When the user has completed reviews in different journals
#      And the user views the abstract pane of their completed reviews
#     Then the current page is tested for A11y


#  @reviewerhubregression @wip //TODO - Need to rethink if necessary in its current form, as it's covered already
#  Scenario: Verify users of Reviewer Hub have completed reviews in a specific journal
#    Given a user logs in to the Reviewer Hub
#      And the user has completed reviews in a specific journal
#     Then the user is able to view and access their completed reviews in a specific journal

#  @reviewerhubregression
#  Scenario: Verify users of Reviewer Hub are able to view the top panel of all journals //TODO - Add this to an existing test (an extra assertion maybe)
#    Given a user logs in to the Reviewer Hub
#      And the user has completed reviews in a specific journal
#     Then the user is able to view the top panel of all journals
#      | Journal Cover Image                                   |
#      | Total number of reviews completed for that journal    |
#      | Total number of journals reviewed                     |

#  @reviewerhubregression
#  Scenario: Verify users of Reviewer Hub are able to view the top panel of a specific journal //TODO - Add this to an existing test (an extra assertion maybe)
#    Given a user logs in to the Reviewer Hub
#    And the user has completed reviews in a specific journal
#    Then the user is able to view the top panel of a specific journal
#      | Journal Cover Image                                   |
#      | Journal Name                                          |
#      | Total number of reviews completed for that journal    |

  @reviewerhubregression @wip
  Scenario: Verify certificate can be downloaded
    Given a user logs in to the Reviewer Hub
      And the user has completed reviews in different journals
     Then they should be able to download reviewer certificates at the journal level
      And the reviewer certificate contains
#      | Journal Title           |
#      | Date Awarded            |
#      | Total Number Of Reviews |
#      | Name of the Reviewer    |

#  Scenario: TODO - Accepted Review invitation appears in the Reviews in Progress section

#  Scenario: TODO - Completed Review in Progress appears in Reviews History section

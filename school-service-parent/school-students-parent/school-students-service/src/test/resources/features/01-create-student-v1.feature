Feature: Create new student
    I want to create new student

    Scenario Outline: Create student <TC>
        Given The endpoint "/students/v1"
        And The resource CreateStudentRequestResourceV1
        And The input data
            | firstName      | lastName   | email   |
            | <firstName>    | <lastName> | <email> |
        When Make "POST" call
        Then I verify the <responseStatusCode> response code
        And If response code not 201 i verify the error code "<errorCode>"
        And If response code is 201 i save the resource

        Examples: 
            | TC    | firstName                  | lastName                   | email                                                | responseStatusCode    | errorCode                |
            | TC01  | Frodo                      | Bolson                     | frodo@mail.com                                       | 201                   |                          |
            | TC02  | Frodo                      | Bolson                     | frodo@mail.com                                       | 400                   | 001001016                |
            | TC03  |                            | Bolson                     | frodo@mail.com                                       | 400                   | 001001003                |
            | TC04  | a                          | Bolson                     | frodo@mail.com                                       | 400                   | 001001007                |
            | TC05  | abcdefghijabcdefghijabcdef | Bolson                     | frodo@mail.com                                       | 400                   | 001001007                |
            | TC06  | Frodo                      |                            | frodo@mail.com                                       | 400                   | 001001004                |
            | TC07  | Frodo                      | a                          | frodo@mail.com                                       | 400                   | 001001008                |
            | TC08  | Frodo                      | abcdefghijabcdefghijabcdef | frodo@mail.com                                       | 400                   | 001001008                |
            | TC09  | Frodo                      | Bolson                     |                                                      | 400                   | 001001005                |
            | TC10  | Frodo                      | Bolson                     | a                                                    | 400                   | 001001009                |
            | TC11  | Frodo                      | Bolson                     | abcdefghijabcdefghijabcdefghijabcdefghijabcdefghija  | 400                   | 001001009                |
            | TC12  | Frodo                      | Bolson                     | frodomailcom                                         | 400                   | 001001010                |


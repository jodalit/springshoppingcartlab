CREATE VIEW Person_Customer_view AS (SELECT
        C.customerId,
        C.personId,
        P.personName,
        P.personConnectionName,
        P.personPassword,
        C.customerAvailableAmount,
        P.profileId
    FROM
        (Customer C
        LEFT JOIN Person_Profile_view P ON ((C.personId = P.personId)))
    WHERE
        (P.profileId = 2));
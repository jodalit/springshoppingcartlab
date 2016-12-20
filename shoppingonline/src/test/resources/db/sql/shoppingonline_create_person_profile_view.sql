CREATE VIEW Person_Profile_view AS (SELECT
        p1.personId,
        p1.personName,
        p1.personSex,
        p1.personConnectionName,
        p1.personPassword,
        p.profileId,
        p.profileName
    FROM
        (Profile p
        LEFT JOIN Person p1 ON ((p.profileId = p1.personProfile))
        ));
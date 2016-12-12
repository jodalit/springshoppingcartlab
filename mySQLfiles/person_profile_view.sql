CREATE 
    ALGORITHM = UNDEFINED 
    DEFINER = `shoppingcart_JD`@`%` 
    SQL SECURITY DEFINER
VIEW `Person_Profile_view` AS
    SELECT distinct 
        `p1`.`personId` AS `personId`,
        `p1`.`personName` AS `personName`,
        `p1`.`personSex` AS `personSex`,
        `p1`.`personConnectionName` AS `personConnectionName`,
        `p1`.`personPassword` AS `personPassword`,
        `p`.`profileId` AS `profileId`,
        `p`.`profileName` AS `profileName`
    FROM
        (`Profile` `p`
        LEFT JOIN `Person` `p1` ON ((`p`.`profileId` = `p1`.`personProfile`)))
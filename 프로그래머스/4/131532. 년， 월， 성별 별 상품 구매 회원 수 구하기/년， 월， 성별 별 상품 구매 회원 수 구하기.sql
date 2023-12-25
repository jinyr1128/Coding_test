SELECT 
    YEAR(OS.SALES_DATE) AS YEAR,
    MONTH(OS.SALES_DATE) AS MONTH,
    CASE
        WHEN UI.GENDER = 0 THEN '0'
        WHEN UI.GENDER = 1 THEN '1'
    END AS GENDER,
    COUNT(DISTINCT UI.USER_ID) AS USERS
FROM 
    USER_INFO UI
JOIN 
    ONLINE_SALE OS
ON 
    UI.USER_ID = OS.USER_ID
WHERE 
    UI.GENDER IS NOT NULL
GROUP BY 
    YEAR(OS.SALES_DATE), 
    MONTH(OS.SALES_DATE), 
    UI.GENDER
ORDER BY 
    YEAR(OS.SALES_DATE), 
    MONTH(OS.SALES_DATE), 
    UI.GENDER;


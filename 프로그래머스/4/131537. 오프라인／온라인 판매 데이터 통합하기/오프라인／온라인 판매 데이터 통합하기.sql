SELECT 
    DATE_FORMAT(SALES_DATE, '%Y-%m-%d') AS SALES_DATE, 
    PRODUCT_ID, 
    USER_ID, 
    SALES_AMOUNT
FROM 
    (SELECT 
        SALES_DATE, 
        PRODUCT_ID, 
        USER_ID, 
        SALES_AMOUNT
    FROM 
        ONLINE_SALE 
    WHERE 
        DATE_FORMAT(SALES_DATE, '%Y%m') = '202203'
    
    UNION ALL
    
    SELECT 
        SALES_DATE, 
        PRODUCT_ID, 
        NULL AS USER_ID, 
        SALES_AMOUNT
    FROM 
        OFFLINE_SALE
    WHERE 
        DATE_FORMAT(SALES_DATE, '%Y%m') = '202203') AS TOTAL_SALES
ORDER BY 
    SALES_DATE, 
    PRODUCT_ID, 
    USER_ID;
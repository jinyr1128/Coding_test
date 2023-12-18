SELECT CATEGORY, SUM(SALES) AS TOTAL_SALES
FROM BOOK_SALES
JOIN BOOK ON BOOK_SALES.BOOK_ID = BOOK.BOOK_ID
WHERE EXTRACT(YEAR FROM SALES_DATE) = 2022 AND EXTRACT(MONTH FROM SALES_DATE) = 1
GROUP BY CATEGORY
ORDER BY CATEGORY ASC;

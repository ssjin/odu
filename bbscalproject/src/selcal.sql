

SELECT * FROM CALENDAR



SELECT SEQ, ID, TITLE, CONTENT, RDATE, WDATE
FROM(
	SELECT ROW_NUMBER() OVER(PARTITION BY SUBSTR(RDATE, 1, 8) ORDER BY RDATE ASC) RN, SEQ, ID, TITLE, CONTENT, RDATE, WDATE FROM CALENDAR
	WHERE ID='aaa' AND SUBSTR(RDATE, 1, 6)='201607'
	)
WHERE RN BETWEEN 1 AND 5;
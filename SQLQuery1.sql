SELECT COUNT(foodId) as totalRows from Food F 
WHERE F.statusId = 1 
 AND F.createDate > '2020-10-15'

 And F.categoriId = 1
 And F.foodPrice >= 1000
 And F.foodPrice <= 66666


 INSERT INTO Food (foodname,foodPrice, quantity, categoriId, description, createDate, imageLink ,statusId) 
 VALUES(?, ?, ?, ?, ?, ?, ?, ?) ;


 SELECT F.foodId, F.foodname , F.foodPrice , F.quantity, F.description, F.createDate , F.categoriId , F.imageLink
  From Food F 
  WHERE F.statusId = 1 
   AND F.createDate > '2020-5-15'
   And F.categoriId = 1
   And F.foodPrice >= 1000
   And F.foodPrice <= 66666


   SELECT F.foodId, F.foodname , F.foodPrice , F.quantity, F.description, F.createDate , F.categoriId , F.imageLink
   From Food F 
   WHERE F.statusId = 1 and f.foodId = 2

   UPDATE Food SET statusId = 2 WHERE foodId = ?

   UPDATE Food SET foodname = ? , foodPrice = ? , quantity = ? , description = ? , imageLink = ? , categoriId = ?  WHERE foodId = ?
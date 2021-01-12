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
  WHERE F.statusId = 1 and F.quantity > (SELECT COUNT(B.Amount) AS Amount 
  										  From BookingDetail B
										  Where B.FoodId = F.foodId)
   And F.categoriId = 1
   And F.foodPrice >= 1000
   And F.foodPrice <= 66666

 SELECT SUM(B.Amount) AS Amount 
 From BookingDetail B
 Where B.FoodId = 16
 Group by b.FoodId

 SELECT SUM(b.Amount) as TotalBooked FROM BookingDetail b WHERE b.FoodId = 16

   SELECT F.foodId, F.foodname , F.foodPrice , F.quantity, F.description, F.createDate , F.categoriId , F.imageLink
   From Food F 
   WHERE F.statusId = 1 and f.foodId = 2

   UPDATE Food SET statusId = 2 WHERE foodId = ?

   UPDATE Food SET foodname = ? , foodPrice = ? , quantity = ? , description = ? , imageLink = ? , categoriId = ?  WHERE foodId = ?


   SELECT COUNT(b.Id) as TotalBooked FROM BookingDetail b WHERE b.FoodId = ?


INSERT INTO BookingDetail (BookingId,FoodId,Amount) 
 VALUES(?, ?, ?)

 SELECT quantity  FROM Food Where foodId = ? 4 


 -- show history theo userID

 select b.ImportedDate as DateBuy  , f.foodname as ProductName , bd.Amount as ProductQuantity
 from Booking b , BookingDetail bd , Food f
 where b.Id = bd.BookingId and f.foodId = bd.FoodId and b.UserId = 2

 -- load tất cả history theo đơn hàng của khách hàng
 select b.Id as MaDonHang, b.ImportedDate , b.Total 
 from Booking b 
 where b.UserId = 2

 -- load detail của từng đơn hàng
 select b.FoodId ,  b.Amount as soLuong
 from BookingDetail b ,Food f
 where f.foodId = b.FoodId and b.BookingId = 1 

 select foodname from Food  where foodId = 3


 SELECT COUNT(foodId) as totalRows from Food F 
                    WHERE F.statusId = 1 and F.quantity > 0
                    And F.quantity > (SELECT Count(B.Amount) AS Amount 
									  From BookingDetail B 
									  Where B.FoodId = F.foodId) 
					 And F.quantity > (SELECT Sum(B.Amount) AS Amount 
									  From BookingDetail B 
									  Where B.FoodId = F.foodId) 
									   
 select *  from Food where  foodId = 16 and quantity  > (SELECT SUM(b.Amount) as Amount FROM BookingDetail b WHERE b.FoodId = 16)

 SELECT F.foodId, F.foodname , F.foodPrice , F.quantity, F.description, F.createDate , F.categoriId , F.imageLink
                        From Food F 
                        WHERE F.statusId = 1 
                         AND F.quantity >  0
                         And F.quantity > (SELECT Count(B.Amount) AS Amount 
										    From BookingDetail B 
											Where B.FoodId = F.foodId)
select b.FoodId , b.Amount as soLuong 
from BookingDetail b ,Food f 
where f.foodId = b.FoodId and b.BookingId = 1011

select b.Id as MaDonHang, b.ImportedDate , b.Total  
                         from Booking b  
                         where b.UserId = 2

-- load theo IDBooking lấy detail
select bk.Id,  bk.ImportedDate, bk.Total,b.FoodId , f.foodname, b.Amount
from BookingDetail b ,Food f , Booking bk
where f.foodId = b.FoodId and  b.BookingId  = bk.Id and bk.Id = 1011

select b.FoodId , f.foodname, b.Amount
from BookingDetail b ,Food f 
where b.FoodId = f.foodId and b.Id = 1008

select b.FoodId , b.Amount as soLuong 
from BookingDetail b ,Food f 
where f.foodId = b.FoodId and b.BookingId = 1011

select  Id  as MaDonHang, ImportedDate , Total
from Booking


select b.Id as MaDonHang, b.ImportedDate , b.Total  
from Booking b  
where b.UserId = 2 and b.ImportedDate > '2021-01-5' and b.ImportedDate <= '2021-01-10'
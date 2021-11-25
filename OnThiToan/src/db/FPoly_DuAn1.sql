CREATE DATABASE FPoly_DuAn1
GO
USE FPoly_DuAn1
GO
create table Nguoi_Dung(
	ID_MaND int not null identity(1,1) primary key,
	HoTen nvarchar(40) not null,
	GioiTinh nvarchar(10) not null,
	SDT varchar(15) not null,
	NgaySinh date not null,
	Email nvarchar(40) not null
)
go

create table Tai_Lieu(
	ID_MaTaiLieu int not null identity(1,1) primary key,
	TenTaiLieu nvarchar(40) not null,
	LinkVideo varchar(200) not null,
	LyThuyet nvarchar(200) not null
)
go

create table Tai_Khoan(
	TenTaiKhoan varchar(20) not null primary key,
	MatKhau nvarchar(30) not null,
	MKCap2 nvarchar(30) not null,
	VaiTro bit default(0) not null,
	ID_MaND int not null,
	foreign key (ID_MaND) references Nguoi_Dung (ID_MaND)
)
go

create table Cau_Hoi(
	ID_CauHoi int not null identity(1,1) primary key,
	Role_ID bit default(0),
	CauHoi nvarchar(250) not null,
	DoKho int not null,
	TenBai nvarchar(30),
	DapAn varchar(2),
	NgayTao date not null default(getdate())
)
go

create table Bai_Thi(
	ID_BaiThi int not null identity(1,1) primary key,
	MaDe int not null,
	DoKho int not null,
	ID_CauHoi int not null,
	foreign key (ID_CauHoi) references Cau_Hoi (ID_CauHoi)

)
go
create table ChiTiet_BaiThi(
	ID_MaND int not null identity(1,1) primary key,
	ID_BaiThi int not null,
	SoCauDung int not null,
	SoCauSai int not null,
	Diem float not null,
	NgayThi date not null default(getdate()),
	foreign key (ID_BaiThi) references Bai_Thi (ID_BaiThi),
	foreign key (ID_MaND) references Nguoi_Dung (ID_MaND)

)
create table Bai_Tap(
	ID_BaiTap int not null identity(1,1) primary key,
	DoKho int ,
	ID_CauHoi int not null,
	foreign key (ID_CauHoi) references Cau_Hoi (ID_CauHoi)

)
go
create table ChiTiet_BaiTap(
	ID_MaND int not null identity(1,1) primary key,
	ID_BaiTap int not null,
	SoCauDung int not null,
	SoCauSai int not null,
	NgayLam date not null default(getdate()),
	foreign key (ID_BaiTap) references Bai_Tap (ID_BaiTap),
	foreign key (ID_MaND) references Nguoi_Dung (ID_MaND)

)
INSERT INTO Nguoi_Dung (HoTen,GioiTinh,SDT,NgaySinh,Email)
VALUES (N'Hạ Việt Anh','Nam','0984297473','2002-09-04','anhhvph14045@gmail.com'),
		(N'Lê Hồng Quân','Nam','0987654321','2000-01-01','hongquan@gmail.com'),
		(N'Lê Văn Anh',N'Nữ','0322454485','2002-08-21','anhlv22@gmail.com'),
		(N'Nguyễn Huy Hoàng',N'Nữ','0331246589','2002-07-11','hoang2404@gmail.com'),
		(N'Đinh Công Trường',N'Nữ','0983453489','2002-11-21','truongdc@gmail.com')
go
INSERT INTO Tai_Khoan(TenTaiKhoan,MatKhau,MKCap2,VaiTro,ID_MaND)
VALUES ('vietanhvs','492002','2002',1,1),
	('hongquan','12345','2000',1,2),
	('vananh','12345','2002',0,3),
	('huyhoang','12345','2002',1,4),
	('congtruong','12345','2002',0,5)
go
INSERT INTO Tai_Lieu(TenTaiLieu,LinkVideo,LyThuyet)
VALUES (N'Đạo Hàm','https://www.youtube.com/watch?v=HIGllE3N-iw',
	'https://loigiaihay.com/ly-thuyet-dinh-nghia-va-y-nghia-cua-dao-ham-c46a5878.html'),
	(N'Nguyên Hàm','https://www.youtube.com/watch?v=ZgxXaJMg9vQ',
	'https://vungoi.vn/lop-12/chi-tiet-ly-thuyet-nguyen-ham-5af3eae81261631175a05d3e.html')
go

INSERT INTO Cau_Hoi(Role_ID,CauHoi,DoKho)
VALUES (1,'Hinh/CauHoi/anh1-121.png',1),
	(1,'Hinh/CauHoi/anh2-121.png',1),
	(1,'Hinh/CauHoi/anh3-121.png',1),
	(1,'Hinh/CauHoi/anh4-121.png',2),
	(1,'Hinh/CauHoi/anh5-121.png',3),
	(1,'Hinh/CauHoi/anh6-121.png',3),
	(1,'Hinh/CauHoi/anh7-121.png',1),
	(1,'Hinh/CauHoi/anh4-121.png',2),
	(1,'Hinh/CauHoi/anh5-121.png',3),
	(1,'Hinh/CauHoi/anh6-121.png',3)
go

INSERT INTO Cau_Hoi(Role_ID,CauHoi,DoKho,TenBai,DapAn)
VALUES 
	(0,'Hinh/CauHoi/bai1.png',1,N'Đạo Hàm','A'),
	(0,'Hinh/CauHoi/bai2.png',2,N'Đạo Hàm','B'),
	(0,'Hinh/CauHoi/bai3.png',3,N'Đạo Hàm','C'),
	(0,'Hinh/CauHoi/bai4.png',1,N'Đạo Hàm','A'),
	(0,'Hinh/CauHoi/bai5.png',2,N'Đạo Hàm','D'),
	(0,'Hinh/CauHoi/bai6.png',3,N'Đạo Hàm','C')
GO

SELECT*FROM Nguoi_Dung
SELECT*FROM Tai_Khoan
SELECT*FROM Tai_Lieu
SELECT*FROM Cau_Hoi
SELECT TenBai FROM Cau_Hoi GROUP BY TenBai

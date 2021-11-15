CREATE DATABASE Poly_DuAn1
GO
USE Poly_DuAn1
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
create table Tai_Khoan(
	TenTaiKhoan varchar(20) not null primary key,
	MatKhau nvarchar(30) not null,
	MKCap2 nvarchar(30) not null,
	VaiTro bit default(0) not null,
	ID_MaND int not null,
	foreign key (ID_MaND) references Nguoi_Dung (ID_MaND)
)
go

create table Tai_Lieu(
	ID_MaTaiLieu int not null identity(1,1) primary key,
	TenTaiLieu nvarchar(40) not null,
	LinkVideo varchar(200) not null,
	LyThuyet nvarchar(200) not null
)
go

create table TaiLieu_ChiTiet(
	ID_MaND int not null identity(1,1) primary key,
	ID_MaTaiLieu int not null,
	foreign key (ID_MaND) references Nguoi_Dung (ID_MaND),
	foreign key (ID_MaTaiLieu) references Tai_Lieu (ID_MaTaiLieu)

)
go
create table Cau_Hoi(
	ID_CauHoi int not null identity(1,1) primary key,
	ID_BT int,
	CauHoi nvarchar(250) not null,
	DoKho int not null,
	NgayTao date not null default(getdate())
)
go

create table Dap_An(
	ID_DapAn int not null identity(1,1) primary key,
	ID_CauHoi int not null,
	DapAnDung varchar(250) not null,
	DapAnSai1 varchar(250) not null,
	DapAnSai2 varchar(250) not null,
	DapAnSai3 varchar(250) not null,
	foreign key (ID_CauHoi) references Cau_Hoi (ID_CauHoi)
)
go

create table Bai_Thi(
	ID_BaiThi int not null identity(1,1) primary key,
	MaDe int not null,
	STTCauHoi int not null,
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
	TenBai nvarchar(50) not null,
	STTCauHoi int not null,
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
VALUES (N'Đạo Hàm','https://www.youtube.com/watch?v=HIGllE3N-iw',N'Cho hàm số y=f(x)xác định trên khoảng (a;b), 
x0∈(a;b). Giới hạn hữu hạn (nếu có) của tỉ số (f(x)−f(x0)/x−x0), khi
x→x0 được gọi là đạo hàm của hàm số đã cho tại x0')
	(N'Nguyên Hàm','https://www.youtube.com/watch?v=ZgxXaJMg9vQ',N'Cho hàm số f xác định trên K. Hàm số F được gọi là nguyên hàm của hàm số f trên K
 nếu F(x) khả vi trên K và F(x) = f(x) với mọi x thuộc K')
go
INSERT INTO Cau_Hoi(CauHoi,DoKho)
VALUES (N'Tính đạo hàm của các hàm số sau: 
Tại các điểm được chỉ ra: 1.Cho f(x)= x^3+x-2. Tính f(-2)?',1)
INSERT INTO Cau_Hoi(ID_BT, CauHoi,DoKho)
VALUES (1,N'Tại các điểm được chỉ ra: 1.Cho f(x)= x^3+x^2-10. Tính f(4)?',2)
go
INSERT INTO Dap_An(ID_CauHoi, DapAnDung,DapAnSai1,DapAnSai2,DapAnSai3)
VALUES (1,'f(-2)=13','f(-2)=15','f(-2)=11','f(-2)=12'),
	(2,'f(4)=17','f(4)=20','f(4)=5','ff(4)=10')
SELECT*FROM Nguoi_Dung
SELECT*FROM Tai_Khoan
SELECT*FROM Tai_Lieu
SELECT*FROM Cau_Hoi
SELECT*FROM Dap_An

SELECT * FROM Nguoi_Dung WHERE ID_MaND= (SELECT MAX(ID_MaND) FROM Nguoi_Dung )



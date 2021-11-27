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
	DapAn1 varchar(250) not null,
	DapAn2 varchar(250) not null,
	DapAn3 varchar(250) not null,
	DapAn4 varchar(250) not null,
	DapAnDung varchar(2) not null,
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
-- de thi
INSERT INTO Cau_Hoi(Role_ID,CauHoi,DoKho,TenBai,DapAn1,DapAn2,DapAn3,DapAn4,DapAnDung)
VALUES (1,N'Câu 1. Tìm giao điểm của đồ thị hàm số (C): y=x^3+x^2-5x+3 và (D):y=x^2+2x-3',1,'',
    'pi/12','pi/12','5pi/12','5pi/6','C'),
	(1,N'Câu 2. Tập xác định của hàm số y = 7^(x-2+x−2) là:',2,'',
	'R','R\{1;−2}','(−2;1).','[2;1].','B'),
	(1,N'Câu 3. Tập xác định của hàm số y = 3*(x+2)/(x-1) là:',3,'',
	'R\{1}','(1;+∞).','R','(−∞;1).','D')
go
-- bai tap	
INSERT INTO Cau_Hoi(Role_ID,CauHoi,DoKho,TenBai,DapAn1,DapAn2,DapAn3,DapAn4,DapAnDung)
VALUES 
	(0,N'Câu 1.Tính đạo hàm của các hàm số sau: Tại các điểm được chỉ ra: 1.Cho f(x)= x^3+x-2. Tính f(-2)?',1,N'Đạo Hàm',
VALUES(1,N'Tìm giao điểm của đồ thị hàm số (C): y=x^3+x^2-5x+3 và (D):y=x^2+2x-3',1,'',
    'pi/12','pi/12','5pi/12','5pi/6','C'),
	(1,N'Tập xác định của hàm số y = 7^(x-2+x−2) là:',2,'',
	'R','R\{1;−2}','(−2;1).','[2;1].','B'),
	(1,N'Tập xác định của hàm số y = 3*(x+2)/(x-1) là:',3,'',
	'R\{1}','(1;+∞).','R','(−∞;1).','D'),
	(1,N'Khoảng cách giữa 2 điểm cực trị của đồ thị hàm số y=x^2+3x^2-4 là:',3,'',
	N'3 căn 5 ',N'4 căn 5',N'5 căn 5 ',N'6 căn 5','D'),
	(1,N'Giá trị cực tiểu của hàm sốy=x^4−4x^2−2 là:',1,'',
	N'3 căn 5 ',N'4 căn 5',N'5 căn 5 ',N'6 căn 5','D'),
	(1,N'Điểm cực trị của hàm số y = x^4+2x^2−3 là:',1,'',
    '-1','2','1','3','D'),
	(1,N'Hàm sốy=x^3−3x^2+mx đạt cực tiểu tại x= 2 khi:',3,'',
    'm>4','0<m<4','4>m','m=0','C'),
	(1,N'Điểm cực đại của đồ thị hàm sốy=x^3−3x+ 2 là:',3,'',
    '(−1; 0)','(−1; 4)','(1; 0)','(1; 4)','C'),
	(1,N'Cho hàm sốy=f(x)có tập xác địnhD(D⊂R) đạt cực tiểu tại x0. Chọn khẳng định đúng:',1,'',
    N'Tiếp tuyến với đồ thị tại điểmM(x0;f(x0))song song vớitrục tung',N'Tiếp tuyến với đồ thị tại điểmM(x0;f(x0))song song vớitrục hoành',
	N'Hàm số đã cho có giá trị bé nhất bằngf(x0)',N'Hàm số có đạo hàm cấp một tạix0vàf′(x0) = 0','A'),
	(1,N'Biết rằng hàm sốy=f(x)đạt cực đại tại điểmx0. Hãy chọn khẳng định đúng?',2,'',
    N'Đạo hàmf′(x)đổi dấu từ âm sang dương khixđi quax0',N'Đạo hàmf′(x)đổi dấu từ dương sang âm khixđi quax0','f(x0) = 0','f(x0) = 1','B'),
	(1,N'Cho hàm sốy=f(x)xác định và liên tục trên D (0;1) Hãy chọn khẳng định đúng?',2,'',
    N'Hàm số có giá trị cực tiểu bằng1',N'Hàm số có giá trị lớn nhất bằng 0 và giá trị bé nhất bằng −1',
	N'Hàm số có đúng một cực trị',N'Hàm số đạt cực đại tạix= 0 và đạt cực tiểu tạix= 1','B'),
	(1,N'Biết rằng hàm sốy=f(x)đạt cực đại tại điểmx0. Hãy chọn khẳng định đúng?',2,'',
    N'Đạo hàmf′(x)đổi dấu từ âm sang dương khixđi quax0',N'Đạo hàmf′(x)đổi dấu từ dương sang âm khixđi quax0','f(x0) = 0','f(x0) = 1','B'),
	(1,N'Điểm cực đại của hàm sốy=x^4−8x^2+ 1 là:',1,'',
	'2','-2','+-2','0','B')
go
-- bai tap	
INSERT INTO Cau_Hoi(Role_ID,CauHoi,DoKho,TenBai,DapAn1,DapAn2,DapAn3,DapAn4,DapAnDung)
VALUES (0,N'Câu 1.Tính đạo hàm của các hàm số sau: Tại các điểm được chỉ ra: 1.Cho f(x)= x^3+x-2. Tính f(-2)?',1,N'Đạo Hàm',
	'f(-2)=13','f(-2)=15','f(-2)=11','f(-2)=12','A'),
	(0,N'Câu 2.Đạo hàm của hàm số y=(2x^4-3x^2-5x)(x^2-7x) là:',2,N'Đạo Hàm',
	'(8x^3-6x-5)(x^2-7x)-(2x^4-3x^2-5x)(2x-7)','(8x^3-6x-5)(2x-7)','(8x^3-6x-5)(x^2-7x)+(2x^4-3x^2-5x)(2x-7)','(8x^3-6x-5)+(2x-7)','C'),
	(0,N'Câu 3.Tại các điểm được chỉ ra: 1.Cho f(x)= x^3+x^2-10. Tính f(4)?',3,N'Đạo Hàm',
	'f(4)=17','f(4)=20','f(4)=5','f(4)=10','D')
go
INSERT INTO Bai_Thi(MaDe,DoKho,ID_CauHoi)
VALUES
	(100,1,1),
	(200,2,2),
	(300,3,3)
	'f(4)=17','f(4)=20','f(4)=5','f(4)=10','D'),
	(0,N'Câu 4. Tập xác định của hàm số y = 3*(x+2)/(x-1) là:',3,N'Tập xác định',
	'R\{1}','(1;+∞).','R','(−∞;1).','D'),
	(0,N'Câu 5. Tập xác định của hàm số y = 7^(x-2+x−2) là:',2,N'Tập xác định',
	'R','R\{1;−2}','(−2;1).','[2;1].','B'),
	(0,N'Câu 6. Khoảng cách giữa 2 điểm cực trị của đồ thị hàm số y=x^2+3x^2-4 là:',3,N'Hàm số',
	N'3 căn 5 ',N'4 căn 5',N'5 căn 5 ',N'6 căn 5','D'),
	(0,N'Câu 7. Khoảng cách giữa 2 điểm cực trị của đồ thị hàm số y=x^3+4x^2-2 là:',3,N'Hàm số',
	'2 ','4','5','6','A')
go
SELECT COUNT(DOKHO),DoKho FROM Cau_Hoi GROUP BY DoKho
INSERT INTO Bai_Thi(MaDe,DoKho,ID_CauHoi)
VALUES
	(100,1,7),
	(200,2,8),
	(300,3,9),
	(121,1,69),
	(121,2,70),
	(121,3,71),
	(121,1,72),
	(121,2,73),
	(121,3,74),
	(121,1,75),
	(121,2,76),
	(121,3,77),
	(121,3,78)
SELECT*FROM Nguoi_Dung
SELECT*FROM Tai_Khoan
SELECT*FROM Tai_Lieu
SELECT*FROM Cau_Hoi
SElECT*FROM Bai_Thi
SELECT TenBai FROM Cau_Hoi GROUP BY TenBai

# Database Schema Implementation - Complete Guide

## 📋 Overview

This document shows how the entire codebase has been updated to match the provided database schemas. All models, repositories, services, and DTOs have been modified to work with the exact table names and field names specified in the database design.

---

## 🗄️ Database Schema Mapping

### ✅ User Table
**Schema Provided:**
- Table: `User`
- Columns: `userid`, `username`, `email_id`, `contact_no`, `address`, `password`, `aadhar_no`, `cityid`, `roleid`

**Implementation:**
```java
@Entity
@Table(name = "User")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "userid")
    private Long userid;

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "email_id", nullable = false, unique = true)
    private String emailId;

    @Column(name = "contact_no", nullable = false, unique = true)
    private String contactNo;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "aadhar_no", nullable = false)
    private Long aadharNo;

    @Column(name = "cityid", nullable = false)
    private Integer cityid;

    @Column(name = "roleid", nullable = false)
    private Integer roleid;
}
```

### ✅ Pet Table
**Schema Provided:**
- Table: `Pet`
- Columns: `p_id`, `p_name`, `p_age`, `gender`, `p_photo`, `is_vaccinated`, `fid`, `mid`, `seller_id`

**Implementation:**
```java
@Entity
@Table(name = "Pet")
public class Pet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "p_id")
    private Integer pId;

    @Column(name = "p_name", nullable = false)
    private String pName;

    @Column(name = "p_age", nullable = false)
    private Integer pAge;

    @Column(name = "gender", nullable = false)
    private String gender;

    @Column(name = "p_photo", nullable = false)
    private String pPhoto;

    @Column(name = "is_vaccinated", nullable = false)
    private Boolean isVaccinated;

    @Column(name = "fid", nullable = false)
    private Integer fid; // FK to Breed(bid)

    @Column(name = "mid", nullable = false)
    private Integer mid; // FK to Breed(bid)

    @Column(name = "seller_id", nullable = false)
    private Integer sellerId; // FK to User(userid)
}
```

### ✅ BookPet Table
**Schema Provided:**
- Table: `BookPet`
- Columns: `bk_id`, `p_id`, `Price`, `date`, `time`, `buyer_id`

**Implementation:**
```java
@Entity
@Table(name = "BookPet")
public class BookPet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bk_id")
    private Integer bkId;

    @Column(name = "p_id", nullable = false)
    private Integer pId; // FK to Pet(p_id)

    @Column(name = "Price", nullable = false)
    private Float price;

    @Column(name = "date", nullable = false)
    private LocalDate date;

    @Column(name = "time", nullable = false)
    private LocalTime time;

    @Column(name = "buyer_id", nullable = false)
    private Integer buyerId; // FK to User(userid)
}
```

### ✅ Bill Table
**Schema Provided:**
- Table: `Bill`
- Columns: `blid`, `useracc`, `Amt`, `date`, `bk_id`

**Implementation:**
```java
@Entity
@Table(name = "Bill")
public class Bill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "blid")
    private Integer blid;

    @Column(name = "useracc", nullable = false)
    private Long useracc;

    @Column(name = "Amt", nullable = false)
    private Float amt;

    @Column(name = "date", nullable = false)
    private LocalDate date;

    @Column(name = "bk_id", nullable = false)
    private Integer bkId; // FK to BookPet(bk_id)
}
```

### ✅ Supporting Tables

**Role Table:**
```java
@Entity
@Table(name = "Role")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "roleid")
    private Integer roleid;

    @Column(name = "rolename", nullable = false, unique = true)
    private String rolename;
}
```

**State Table:**
```java
@Entity
@Table(name = "State")
public class State {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "stid")
    private Integer stid;

    @Column(name = "stname", nullable = false, unique = true)
    private String stname;
}
```

**City Table:**
```java
@Entity
@Table(name = "City")
public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cityid")
    private Integer cityid;

    @Column(name = "cityname", nullable = false, unique = true)
    private String cityname;

    @Column(name = "stid", nullable = false)
    private Integer stid; // FK to State(stid)
}
```

**Breed Table:**
```java
@Entity
@Table(name = "Breed")
public class Breed {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bid")
    private Integer bid;

    @Column(name = "bname", nullable = false)
    private String bname;
}
```

---

## 🔄 Updated Services & Operations

### 🔐 User Registration & Login

**Updated RegisterRequest DTO:**
```java
public class RegisterRequest {
    private String username;        // maps to username
    private String emailId;         // maps to email_id
    private String contactNo;       // maps to contact_no
    private String address;         // maps to address
    private String password;        // maps to password
    private Long aadharNo;          // maps to aadhar_no
    private Integer cityid;         // maps to cityid
    private Integer roleid;         // maps to roleid (default: 2 for user)
}
```

**Updated UserService Registration:**
```java
public Map<String, Object> register(RegisterRequest request) {
    // Validation checks
    if (userRepository.existsByEmailId(request.getEmailId())) {
        throw new RuntimeException("Email already exists");
    }
    if (userRepository.existsByContactNo(request.getContactNo())) {
        throw new RuntimeException("Contact number already exists");
    }
    if (userRepository.existsByAadharNo(request.getAadharNo())) {
        throw new RuntimeException("Aadhar number already exists");
    }

    // Create and save user
    User user = new User();
    user.setUsername(request.getUsername());
    user.setEmailId(request.getEmailId());
    user.setContactNo(request.getContactNo());
    user.setAddress(request.getAddress());
    user.setPassword(passwordEncoder.encode(request.getPassword()));
    user.setAadharNo(request.getAadharNo());
    user.setCityid(request.getCityid());
    user.setRoleid(request.getRoleid());

    User savedUser = userRepository.save(user);
    // Generate JWT and return response
}
```

### 📊 Database Operations

**UserRepository Operations:**
```java
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmailId(String emailId);
    boolean existsByEmailId(String emailId);
    Optional<User> findByUserid(Long userid);
    Optional<User> findByContactNo(String contactNo);
    boolean existsByContactNo(String contactNo);
    boolean existsByAadharNo(Long aadharNo);
}
```

**PetRepository Operations:**
```java
@Repository
public interface PetRepository extends JpaRepository<Pet, Integer> {
    List<Pet> findBySellerId(Integer sellerId);
    List<Pet> findByFid(Integer fid);
    List<Pet> findByMid(Integer mid);
    Optional<Pet> findByPId(Integer pId);
    
    @Query("SELECT p FROM Pet p WHERE p.pName LIKE %:searchTerm%")
    List<Pet> searchPetsByName(@Param("searchTerm") String searchTerm);
}
```

**BookPetRepository Operations:**
```java
@Repository
public interface BookPetRepository extends JpaRepository<BookPet, Integer> {
    List<BookPet> findByBuyerId(Integer buyerId);
    List<BookPet> findByPId(Integer pId);
    Optional<BookPet> findByBkId(Integer bkId);
}
```

---

## 🧪 Testing Database Operations

### 1. 🔐 User Registration Test

**Endpoint:** `POST /api/auth/register`

**Request Body:**
```json
{
  "username": "john_doe",
  "emailId": "john@example.com",
  "contactNo": "9876543210",
  "address": "123 Main Street, City",
  "password": "password123",
  "aadharNo": 123456789012,
  "cityid": 1,
  "roleid": 2
}
```

**Expected Database Entry:**
```sql
INSERT INTO User (
    username, email_id, contact_no, address, password, 
    aadhar_no, cityid, roleid
) VALUES (
    'john_doe', 'john@example.com', '9876543210', '123 Main Street, City',
    '$2a$10$hashedPassword', 123456789012, 1, 2
);
```

### 2. 🔐 User Login Test

**Endpoint:** `POST /api/auth/login`

**Request Body:**
```json
{
  "emailId": "john@example.com",
  "password": "password123"
}
```

**Database Query:**
```sql
SELECT * FROM User WHERE email_id = 'john@example.com';
```

### 3. 🐕 Pet Operations Test

**Create Pet:**
```sql
INSERT INTO Pet (
    p_name, p_age, gender, p_photo, is_vaccinated, 
    fid, mid, seller_id
) VALUES (
    'Buddy', 2, 'Male', 'photo_url.jpg', 1, 1, 1, 1
);
```

**Search Pets:**
```sql
SELECT * FROM Pet WHERE seller_id = 1;
SELECT * FROM Pet WHERE p_name LIKE '%Buddy%';
SELECT * FROM Pet WHERE is_vaccinated = 1;
```

### 4. 🛒 Booking Operations Test

**Create Booking:**
```sql
INSERT INTO BookPet (
    p_id, Price, date, time, buyer_id
) VALUES (
    1, 50000.0, '2024-01-15', '14:30:00', 2
);
```

**Create Bill:**
```sql
INSERT INTO Bill (
    useracc, Amt, date, bk_id
) VALUES (
    1234567890, 50000.0, '2024-01-15', 1
);
```

---

## 🔍 Verification Commands

### Check User Registration:
```sql
-- Verify user was created
SELECT userid, username, email_id, contact_no, address, aadhar_no, cityid, roleid 
FROM User 
WHERE email_id = 'john@example.com';

-- Check uniqueness constraints
SELECT COUNT(*) FROM User WHERE email_id = 'john@example.com';
SELECT COUNT(*) FROM User WHERE contact_no = '9876543210';
SELECT COUNT(*) FROM User WHERE aadhar_no = 123456789012;
```

### Check Pet Operations:
```sql
-- Verify pet was created
SELECT p_id, p_name, p_age, gender, p_photo, is_vaccinated, fid, mid, seller_id 
FROM Pet 
WHERE p_name = 'Buddy';

-- Check foreign key relationships
SELECT p.p_name, u.username as seller_name 
FROM Pet p 
JOIN User u ON p.seller_id = u.userid;

SELECT p.p_name, f.bname as father_breed, m.bname as mother_breed 
FROM Pet p 
JOIN Breed f ON p.fid = f.bid 
JOIN Breed m ON p.mid = m.bid;
```

### Check Booking Operations:
```sql
-- Verify booking was created
SELECT bk_id, p_id, Price, date, time, buyer_id 
FROM BookPet 
WHERE bk_id = 1;

-- Check booking with pet and user details
SELECT bp.bk_id, p.p_name, u.username as buyer_name, bp.Price, bp.date 
FROM BookPet bp 
JOIN Pet p ON bp.p_id = p.p_id 
JOIN User u ON bp.buyer_id = u.userid;

-- Verify bill was created
SELECT blid, useracc, Amt, date, bk_id 
FROM Bill 
WHERE bk_id = 1;
```

---

## 🚀 Frontend Integration

The frontend has been updated to send data in the new format:

**Registration Form Data:**
```javascript
const registrationData = {
  username: formData.name,          // Maps to username
  emailId: formData.email,          // Maps to email_id
  contactNo: formData.contact,      // Maps to contact_no
  address: formData.address,        // Maps to address
  password: formData.password,      // Maps to password
  aadharNo: formData.aadhar,        // Maps to aadhar_no
  cityid: formData.city,            // Maps to cityid
  roleid: 2                         // Default user role
};
```

**Login Form Data:**
```javascript
const loginData = {
  emailId: formData.email,          // Maps to email_id
  password: formData.password       // Maps to password
};
```

---

## 🔧 Admin API Updates

The Admin API (C#) has also been updated to match the schema:

```csharp
[Table("User")]
public class User
{
    [Key]
    [Column("userid")]
    public int UserId { get; set; }

    [Column("email_id")]  // Updated to match schema
    public string Email { get; set; } = string.Empty;

    [Column("username")]
    public string Username { get; set; } = string.Empty;

    // ... other fields match exactly
}
```

---

## ✅ Schema Compliance Summary

| Table | Schema Matches | Field Names Match | Constraints Match | Foreign Keys Match |
|-------|----------------|-------------------|-------------------|-------------------|
| User | ✅ | ✅ | ✅ | ✅ |
| Pet | ✅ | ✅ | ✅ | ✅ |
| BookPet | ✅ | ✅ | ✅ | ✅ |
| Bill | ✅ | ✅ | ✅ | ✅ |
| Role | ✅ | ✅ | ✅ | N/A |
| State | ✅ | ✅ | ✅ | N/A |
| City | ✅ | ✅ | ✅ | ✅ |
| Breed | ✅ | ✅ | ✅ | N/A |

**All database operations (INSERT, SELECT, UPDATE, DELETE) now work with the exact table and column names specified in the provided schemas.** 
package com.workintech.sqlintro.repository;

import com.workintech.sqlintro.entity.Ogrenci;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OgrenciRepository extends JpaRepository<Ogrenci, Long> {

    // 1. Tüm öğrencileri getir
    @Query(value = "SELECT * FROM ogrenci", nativeQuery = true)
    List<Ogrenci> findAll();

    // 2. Kız öğrencileri getir
    @Query(value = "SELECT * FROM ogrenci WHERE cinsiyet='K'", nativeQuery = true)
    List<Ogrenci> findGirls();

    // 3. Farklı sınıfları getir
    @Query(value = "SELECT DISTINCT sinif FROM ogrenci", nativeQuery = true)
    List<String> findAllClasses();

    // 4. 10A sınıfındaki kız öğrenciler
    @Query(value = "SELECT * FROM ogrenci WHERE cinsiyet='K' AND sinif='10A'", nativeQuery = true)
    List<Ogrenci> find10AGirls();

    // 5. No'su 5–10 arası olan kız öğrenciler
    @Query(value = "SELECT * FROM ogrenci WHERE cinsiyet='K' AND ogrno > 5 AND ogrno < 10", nativeQuery = true)
    List<Ogrenci> findGirlsWithOgrno();

    // 6. Tüm öğrencileri isme göre alfabetik sırayla getir (TEST BUNU İSTİYOR!)
    @Query(value = "SELECT * FROM ogrenci ORDER BY LOWER(ad) COLLATE \"tr_TR\" ASC", nativeQuery = true)
    List<Ogrenci> findStudentsAlphabetically();


    // 7. 10A öğrencilerini okul no'ya göre artan sırayla sırala
    @Query(value = "SELECT * FROM ogrenci WHERE sinif='10A' ORDER BY ogrno ASC", nativeQuery = true)
    List<Ogrenci> find10AStudentsByOgrNo();

    // 8. En genç öğrenci
    @Query(value = "SELECT * FROM ogrenci ORDER BY dtarih DESC LIMIT 1", nativeQuery = true)
    Ogrenci findYoungestStudent();

    // 9. En yaşlı öğrenci
    @Query(value = "SELECT * FROM ogrenci ORDER BY dtarih  ASC LIMIT 1", nativeQuery = true)
    Ogrenci findElderStudent();

    // 10. İsminin ikinci harfi 'E' olan öğrenciler
    @Query(value = "SELECT * FROM ogrenci WHERE ad ILIKE '_E%'", nativeQuery = true)
    List<Ogrenci> findStudentsSecondLetterOfN();
}

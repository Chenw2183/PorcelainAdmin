package com.cwz.ssmspringboot2.dao;

import com.cwz.ssmspringboot2.domain.User;
import com.cwz.ssmspringboot2.domain.User2;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.Mapping;

import java.util.List;


//@Repository、@Service、@Controller 和 @Component 将类标识为Bean
@Repository
@Mapper
public interface UserDao {
    /**
     * 查询7天数据
     *
     * @param date 需要查询的日期后7天所有数据
     * @return
     */
    List<User> SelectAll(String date, int contrastday);

    /**
     * 按照日期 查询区间中全省的GSM无线话务量的指标值
     *
     * @param nowdate
     * @param beforedate
     * @return
     */
    List<User2> ProvinceSelect(String WX_004_XXXX, String nowdate, String beforedate);
    
}

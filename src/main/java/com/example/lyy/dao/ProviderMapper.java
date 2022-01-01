package com.example.lyy.dao;


import com.example.lyy.pojo.Provider;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProviderMapper {
    List<Provider> getProviderList();
    List<Provider> getProviderByCondition(@Param("proCode") String proCode,
                                          @Param("proName") String proName);
    int addProvider(Provider provider);
    Provider findProviderById(@Param("id") Integer id);
    int updateProvider(Provider provider);
    int addProviderandPic(Provider provider);
    int deletePro(int id);
}

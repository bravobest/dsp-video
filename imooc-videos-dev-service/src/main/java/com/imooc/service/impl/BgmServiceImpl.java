package com.imooc.service.impl;


import com.imooc.service.BgmService;
import com.imooc.mapper.BgmMapper;
import com.imooc.pojo.Bgm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BgmServiceImpl implements BgmService {

    @Autowired
    BgmMapper bgmMapper;

    @Override
    public List<Bgm> ListBgm() {
        return bgmMapper.selectAll();
    }

    @Override
    public Bgm queryBgmById(String bgmId) {
        return bgmMapper.selectByPrimaryKey(bgmId);
    }
}

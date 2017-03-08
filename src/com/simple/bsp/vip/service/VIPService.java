package com.simple.bsp.vip.service;

import com.simple.bsp.common.util.DataGridModel;
import com.simple.bsp.vip.dao.VIPDao;
import com.simple.bsp.vip.form.VIPForm;
import com.simple.bsp.vip.po.VIP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by 17854 on 2016/6/2.
 */
@Service("vipService")
public class VIPService
{
    @Autowired
    private VIPDao vipDao;
    public Map<String,Object> getPageList(DataGridModel dgm, VIPForm vipForm, String sidx, String school_id)
    {
        if(sidx!=null)vipForm.setCardType(sidx.substring(sidx.length()-2,sidx.length()));
        return vipDao.getPageList(dgm,vipForm,sidx,school_id);
    }

    public int updateVIP(VIP vip, String user_name)
    {
        return vipDao.updateVIP(vip,user_name);
    }

    public int[] deleteVIPBatch(List<String> vipIdList)
    {
        return vipDao.delVIPBatch(vipIdList);
    }

    public int saveTMoney(String inde, String saveMoney)
    {
       return vipDao.saveTMoney(inde,saveMoney);
    }

    public Map<String,Object> getOne(String id, String school_id)
    {
        return vipDao.one(id,school_id);
    }
}

package com.simple.bsp.statistics.service;

import com.simple.bsp.common.util.DataGridModel;
import com.simple.bsp.statistics.dao.StatisticsDao;
import com.simple.bsp.statistics.form.LibraryCatalog;
import com.simple.bsp.statistics.form.StatisticsForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by 17854 on 2016/6/11.
 */
@Service("statisticsService")
public class StatisticsService
{
    @Autowired
    private StatisticsDao statisticsDao;

    /**
     * 流通率统计
     * @param dgm
     * @param statisticsForm
     * @param school_id
     * @return
     */
    public Map<String,Object> list(DataGridModel dgm, StatisticsForm statisticsForm, String school_id)
    {
        return statisticsDao.list(dgm,statisticsForm,school_id);
    }

    /**
     * 图书借阅率统计
     * @param dgm
     * @param statisticsForm
     * @param school_id
     * @return
     */
    public Map<String,Object> typeList(DataGridModel dgm, StatisticsForm statisticsForm, String school_id)
    {
        return statisticsDao.typeList(dgm,statisticsForm,school_id);
    }

    /**
     * 馆藏统计
     * @param dgm
     * @param
     * @param school_id
     * @return
     */
    public Map<String,Object> catalogList(DataGridModel dgm, LibraryCatalog libraryCatalog, String school_id)
    {
        return statisticsDao.catalogList(dgm,libraryCatalog,school_id);
    }

    public List<Map<String,Object>> getAllType(String show)
    {
        return statisticsDao.getAllType(show);
    }
}

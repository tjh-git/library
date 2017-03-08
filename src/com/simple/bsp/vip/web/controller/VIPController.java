package com.simple.bsp.vip.web.controller;
import com.simple.bsp.vip.form.VIPForm;
import com.simple.bsp.vip.po.VIP;
import com.simple.bsp.vip.service.VIPService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.simple.bsp.common.util.DataGridModel;
/**
 * Created by 17854 on 2016/6/2.
 */
@Controller
public class VIPController
{
    @Autowired
    private VIPService vipService;
    @RequestMapping(value = "/vip/list",method = RequestMethod.GET)
    public String list(Model model)
    {
        return "/bsp/vip/list/vips";
    }
    @RequestMapping(value = "/vip/list/vipList",method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> queryList(DataGridModel dgm, VIPForm vipForm, String sidx, HttpSession httpSession)
    {
        Object temp=httpSession.getAttribute("school_id");
        String school_id=null;
        if(temp!=null)school_id=temp.toString();
        return vipService.getPageList(dgm, vipForm,sidx,school_id);
    }
    @RequestMapping(value = "/vip/list/vipList",method = RequestMethod.GET)
    @ResponseBody
    public Map<String, Object> queryList(String row_id, HttpSession httpSession)
    {
        System.out.println("12345679");
        Object temp=httpSession.getAttribute("school_id");
        String school_id=null;
        if(temp!=null)school_id=temp.toString();
        return vipService.getOne(row_id,school_id);
    }
    @RequestMapping(value = "/vip/list/updatePopWin", method = RequestMethod.GET)
    public String updateWin() {
        return "bsp/vip/list/updatePopWin";
    }
    @RequestMapping(value = "/vip/list/updateVIP", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, String> updateVIP(VIP vip, HttpSession session) {

        Map<String, String> map = new HashMap<String, String>();
        try {
            if (vipService.updateVIP(vip,session.getAttribute("user_name")+"") > 0) {
                map.put("mes", "更新会员信息成功");
            } else {
                map.put("mes", "更新会员信息失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            map.put("mes", "更新会员信息失败");
        }
        return map;
    }
    @RequestMapping(value = "/vip/list/delVIPs", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, String> delTeachers(@RequestParam("inde") List<String> vipIdList) {
        Map<String, String> map = new HashMap<String, String>();
        try {
            int[] result = vipService.deleteVIPBatch(vipIdList);
            int sum = 0;
            for (int j = 0; j < result.length; j++) {
                sum += result[j];
            }
            if (sum == vipIdList.size()) {
                map.put("mes", "删除成功[" + (sum) + "]条用户记录");
            } else {
                map.put("mes", "删除成功[" + (sum) + "]条用户记录");
//                map.put("mes", "删除用户失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            map.put("mes", "删除用户失败");
        }
        return map;//重定向
    }
    @RequestMapping(value = "/vip/list/saveMoney",method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> saveMoney(String readCard)
    {
        if(readCard==null||readCard.length()==0)
        {
            Map<String,Object> map=new HashMap<String, Object>();
            map.put("rows",new ArrayList<>());
            return map;
        }
        DataGridModel dgm=new DataGridModel();
        dgm.setPage(1);
        dgm.setRows(10);
        VIPForm vipForm=new VIPForm();
        vipForm.setReadCard(readCard);
        return vipService.getPageList(dgm,vipForm,null,null);
    }
    @RequestMapping(value = "/vip/list/saveTMoney",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,String> saveTMoney(String id,String saveMoney)
    {
        Map<String, String> map = new HashMap<String, String>();
        if(!saveMoney.matches("^(([0-9]|([1-9][0-9]{0,9}))((\\.[0-9]{1,2})?))$")) {
            map.put("mes", "充值失败,请输入有效金额");
            return map;
        }
        try {
            if (vipService.saveTMoney(id,saveMoney)>0)
            {
                map.put("mes", "充值成功,已充值"+saveMoney+"元");
            } else {
                map.put("mes", "充值失败,请输入有效金额");
            }
        } catch (Exception e) {
            e.printStackTrace();
            map.put("mes", "充值失败,请输入有效金额");
        }
        return map;
    }
    @RequestMapping(value = "/vip/list/update",method = RequestMethod.GET)
    public String edit(String id, HttpSession session, HttpServletRequest request)
    {
        System.out.println("我 部  署！！");
        Map<String, Object> oneVip=vipService.getOne(id,session.getAttribute("school_id")+"");
        Map<String, Object> vip=((List<Map<String, Object>>)oneVip.get("rows")).get(0);
        System.out.println(vip);
        request.setAttribute("vip",vip);
        if(vip.get("cardType").toString().equals("教师"))return "bsp/vip_new/teacher/blank";else
        return "bsp/vip_new/student/blank";
    }
    @RequestMapping(value = "/vip/list/edit",method = RequestMethod.POST)
    @ResponseBody
    public Map<String, String> delVIPs(String  oper, VIP vip, HttpSession session)
    {
        if(oper.equals("edit"))
        return updateVIP(vip,session);
        else
        {
            String[] a=vip.getId().split(",");
            List<String> dele=new ArrayList<String>();
            for(int i=0;i<a.length;i++)if(a[i].length()!=0)dele.add(a[i]);
            return delTeachers(dele);
        }
    }
}

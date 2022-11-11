package com.example.videodownload.controller;

import com.example.videodownload.domain.AjaxResult;
import com.example.videodownload.domain.FnUser;
import com.example.videodownload.domain.PhoneCode;
import com.example.videodownload.service.IFnUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.*;

@Controller
public class FunengController {

    public Map<String, PhoneCode> map=new HashMap<>();
    @Autowired
    IFnUserService fnUserService;

    static FnUser cjfn=null;
        //fnUserService.SelectByPhone("888");

    @PostMapping("wx/login")
    @ResponseBody
    public AjaxResult ShengchenCode(String phone,String wxname,String name,String company,String job){
        FnUser fnUser1= fnUserService.SelectByPhone(phone);
        if(fnUser1!=null){
            return AjaxResult.success("ok",fnUser1);

        }
        if(cjfn==null){
            cjfn=fnUserService.SelectByPhone("888");

        }

        SimpleDateFormat myFmt=new SimpleDateFormat("yyyy年MM月dd日");
        Date date=new Date();
        String d= myFmt.format(date);
        FnUser fnUser=new FnUser();
        fnUser.setPhone(phone);
        fnUser.setName(name);
        fnUser.setWxname(wxname);
        fnUser.setDate(d);
        fnUser.setCompany(company);
        fnUser.setJob(job);
         fnUser.setPrize(cjfn.getPrize());
         cjfn.setPrize(String.valueOf(Integer.valueOf(cjfn.getPrize())+1));
        fnUserService.update(cjfn.getName(), cjfn.getWxname(), cjfn.getPhone(),cjfn.getCompany(), cjfn.getDate(), cjfn.getPrize(), cjfn.getRead(), cjfn.getJob());
        fnUserService.insert(fnUser.getName(), fnUser.getWxname(), fnUser.getPhone(), fnUser.getCompany(), fnUser.getDate(), fnUser.getPrize(), fnUser.getRead(), fnUser.getJob());
        return AjaxResult.success("ok",fnUser);
    }
    @PostMapping("wx/cpzl")
    @ResponseBody
    public AjaxResult SelectByphone(String phone){
        FnUser fnUser= fnUserService.SelectByPhone(phone);
        if(fnUser!=null){
            return AjaxResult.success("ok",fnUser);

        }
        return AjaxResult.success("erro");
    }
    @PostMapping("wx/read")
    @ResponseBody
    public AjaxResult updataRead(String phone,String read){
        FnUser fnUser=fnUserService.SelectByPhone(phone);
        if(fnUser!=null){
            if(fnUser.getRead()==null||fnUser.getRead().equals("")){
                    fnUser.setRead(read);
            }else {
                String temp="";
                String str=fnUser.getRead()+","+read;
                String[] arr=  str.split(",");
                List list = new ArrayList();
                for(int x=0;x<arr.length;x++){
                    if(!list.contains(arr[x])){
                        list.add(arr[x]);
                    }
                }
                for (int j=0;j<list.size();j++){
                    temp+= list.get(j);
                    if(j!=list.size()-1) {
                        temp += ",";
                    }
                }
                fnUser.setRead(temp);
            }
            fnUserService.update(fnUser.getName(), fnUser.getWxname(), fnUser.getPhone(), fnUser.getCompany(), fnUser.getDate(), fnUser.getPrize(), fnUser.getRead(), fnUser.getJob());
        }
        return AjaxResult.success("ok");
    }

    @PostMapping("wx/prize")
    @ResponseBody
    public AjaxResult choujiang(String phone){
        FnUser fnUser= fnUserService.SelectByPhone(phone);
        if(cjfn==null){
            cjfn=fnUserService.SelectByPhone("888");

        }

        if(fnUser.getPrize()==null||fnUser.getPrize().equals("")){
            String[] str=cjfn.getRead().split(",");
            List list=new ArrayList();
            for (int i=0;i<str.length;i++){
                list.add(Integer.valueOf(str[i]));
            }
            Integer  p= cj(list);
            fnUser.setPrize(p.toString());
            fnUserService.update(fnUser.getName(), fnUser.getWxname(), fnUser.getPhone(), fnUser.getCompany(), fnUser.getDate(), fnUser.getPrize(), fnUser.getRead(),fnUser.getJob());

        }
      return AjaxResult.success("ok",fnUser);
    }
    public Integer cj(List<Integer> list){
        Integer  count=0;
        for (int i=0;i<list.size();i++){
          count+= list.get(i);
        }
        for(int i=0;i<list.size();i++){
            Integer random= (int) (Math.random()*count);
            if(random<list.get(i)){
                updatap(list,i);
                return i;
            }else {
                count-= list.get(i);
            }
        }
           return -1;
    }
    public void updatap(List<Integer> list,Integer index){
        String temp="";
        for (int i=0;i<list.size();i++)
        {
            Integer l= list.get(i);
            if(i==index){

                l--;
                if (l<=0){
                    l=0;
                }
            }
            temp+=l.toString();
            if(i!= list.size()-1){
                temp+=",";
            }
        }
        cjfn.setRead(temp);
        fnUserService.update(cjfn.getName(), cjfn.getWxname(), cjfn.getPhone(),cjfn.getCompany(), cjfn.getDate(), cjfn.getPrize(), cjfn.getRead(), cjfn.getJob());


    }
    @GetMapping("fn/prize")
    public  String hxprize(){

        return "view/fn/prize";
    }

    @PostMapping("select/prize")
    @ResponseBody
    public AjaxResult SelectByprize(String prize){
        FnUser fnUser=fnUserService.SelectByPrize(prize);
        if(fnUser!=null){
         return   AjaxResult.success("ok");

        }
        return  AjaxResult.success("no");

    }
    @PostMapping("select/hx")
    @ResponseBody
    public AjaxResult SelectByhx(String prize){
        FnUser fnUser=fnUserService.SelectByPrize(prize);
        if(fnUser!=null){
            fnUser.setPrize(fnUser.getPrize()+",ok");
            fnUserService.update(fnUser.getName(), fnUser.getWxname(), fnUser.getPhone(), fnUser.getCompany(), fnUser.getDate(), fnUser.getPrize(), fnUser.getRead(),fnUser.getJob());
            return   AjaxResult.success("ok");

        }
        return  AjaxResult.success("no");

    }
    @PostMapping("wx/saveuser")
    @ResponseBody
    public AjaxResult saveuser(String name){
       // FnUser fnUser1= fnUserService.SelectByPhone(phone);
      //  if(fnUser1!=null){
           // return AjaxResult.success("ok",fnUser1);

      //  }
     //   if(cjfn==null){
            //cjfn=fnUserService.SelectByPhone("888");

      //  }

        SimpleDateFormat myFmt=new SimpleDateFormat("yyyy年MM月dd日HH:mm:ss");
        Date date=new Date();
        String d= myFmt.format(date);
        FnUser fnUser=new FnUser();
        fnUser.setPhone("");
        fnUser.setName(name);
        fnUser.setWxname("植物小程序");
        fnUser.setDate(d);
        fnUser.setCompany("");
        fnUser.setJob("");
        fnUser.setPrize("");

      //  cjfn.setPrize(String.valueOf(Integer.valueOf(cjfn.getPrize())+1));
     //   fnUserService.update(cjfn.getName(), cjfn.getWxname(), cjfn.getPhone(),cjfn.getCompany(), cjfn.getDate(), cjfn.getPrize(), cjfn.getRead(), cjfn.getJob());
        fnUserService.insert(fnUser.getName(), fnUser.getWxname(), fnUser.getPhone(), fnUser.getCompany(), fnUser.getDate(), fnUser.getPrize(), fnUser.getRead(), fnUser.getJob());
        return AjaxResult.success("ok",fnUser);
    }
}

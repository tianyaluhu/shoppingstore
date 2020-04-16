package interceptor;

import entity.WebInfo;
import mapper.WebInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MyInterceptor implements HandlerInterceptor {

    @Autowired
    WebInfoMapper wm;

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        //放行
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
    Date date=new Date();
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM");
        WebInfo wi=wm.selectByVisitTime(sdf.format(date));
        if(wi==null){
            wi=new WebInfo();
            wi.setVisittime(sdf.format(date));
            wi.setVisittimes(1);
            wm.insert(wi);
        }else{
            wm.update(wi);
        }

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}

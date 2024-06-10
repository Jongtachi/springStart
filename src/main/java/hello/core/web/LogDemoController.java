package hello.core.web;

import hello.core.common.MyLogger;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
public class LogDemoController {

    private final LogDemoService logDemoService;
    //private final ObjectProvider<MyLogger> myLoggerProvider;

    private final MyLogger myLogger;

    // 문자를 바로 봔환하기 위해 @ResponseBody 사용
    @RequestMapping("log-demo")
    @ResponseBody
    public String logDemo(HttpServletRequest request) throws InterruptedException {
        String requestURL = request.getRequestURL().toString();
        //MyLogger myLogger = myLoggerProvider.getObject();

        System.out.println("myLogger = " + myLogger.getClass());
        myLogger.setRequestURL(requestURL);

        myLogger.log("controller test");
        
        // 1초 뒤 service 실행 그 사이 다른 고객들이 들어와도 uuid는 고객마다 다르게 유지
        Thread.sleep(1000);
        logDemoService.logic("testId");
        return "OK";
    }
}

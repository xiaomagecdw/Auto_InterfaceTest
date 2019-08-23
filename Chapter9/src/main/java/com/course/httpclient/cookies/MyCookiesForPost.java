package com.sourse.httpclient.cookies;

import org.apache.http.HttpResponse;
import org.apache.http.client.CookieStore;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.cookie.Cookie;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class MyCookiesForPost {

    private  String url;
    private ResourceBundle bundle;
    //�����洢cookies��Ϣ�ı���
    private CookieStore store;



    @BeforeTest
    public void beforeTest(){

        bundle = ResourceBundle.getBundle("application", Locale.CHINA);
        url = bundle.getString("test.url");

    }

    @Test
    public void testGetCookies() throws IOException {

        String result;
        //�������ļ���ƴ�Ӳ��Ե�url
        String uri = bundle.getString("getCookies.uri");
        String testUrl= this.url+uri;

        //�����߼������д
        HttpGet get = new HttpGet(testUrl );
        DefaultHttpClient client = new DefaultHttpClient();
        HttpResponse response = client.execute(get);
        result = EntityUtils.toString(response.getEntity(),"utf-8");
        System.out.println(result);


        //��ȡcookies��Ϣ
        this.store = client.getCookieStore();
        List<Cookie> cookiesList = store.getCookies();
        for (Cookie cookie : cookiesList){
            String name = cookie.getName();
            String value = cookie.getValue();
            System.out.println("cookies name = " + name + ";" + " cookies value = " + value);

        }

    }

    @Test(dependsOnMethods = {"testGetCookies"})
    public void testPostMethod() throws IOException {

        String uri = bundle.getString("test.post.with.cookies");
        String testUrl = this.url+uri;
        //����һ��client�����������з���ִ��
        DefaultHttpClient client =  new DefaultHttpClient();
        //����һ�������������������post����
        HttpPost post = new HttpPost(testUrl);
        //��Ӳ���
        JSONObject parame = new JSONObject();
        parame.put("name","huhansan");
        parame.put("age","18");
        //��������ͷ��Ϣ ����header��Ϣ
        post.setHeader("content-type","application/json");
        //��������Ϣ��ӵ�������
        StringEntity entity = new StringEntity(parame.toString(),"utf-8");
        post.setEntity(entity);
        //����һ��������������Ӧ����Ĵ洢
        String result;
        //����cookies��Ϣ
        client.setCookieStore(this.store);
        //ִ��post����
        HttpResponse response = client.execute(post);
        //��ȡ��Ӧ���
        result = EntityUtils.toString(response.getEntity(),"utf-8");
        System.out.println(result);
        //����Ӧ���ת��json��ʽ
        JSONObject resultJson = new JSONObject(result);
        //��ȡ���ֵ
        String success = (String) resultJson.get("result");
        String status = (String) resultJson.get("status");
        //���ֵ��֤
        Assert.assertEquals("Successful",success);
        Assert.assertEquals("1",status);

    }
}

package ren.superk.zhihu.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import ren.superk.zhihu.core.ZhihuEnum;
import ren.superk.zhihu.model.People;
import ren.superk.zhihu.model.ZhihuPager;
import ren.superk.zhihu.service.PeopleUrlService;

import java.util.List;

@Service
public class PeopleUrlServiceImpl implements PeopleUrlService{
    @Autowired
    private RestTemplate restTemplate;



    @Override
    public  <T> List<T>  findList(String url_token, Integer from, Integer limit, ZhihuEnum followees, Class<T> t){
        String url = "https://www.zhihu.com/api/v4/members/"+url_token+"/"+followees.getValue() ;
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url)
                .queryParam("offset",from)
                .queryParam("limit",limit)
                .queryParam("sort_by","created")
//                .queryParam("sort_by","voteups")
                .queryParam("include", followees.getInclude());
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
        headers.add("authorization", "oauth c3cef7c66a1843f8b3a9e6a1e3160e20");
        HttpEntity requestEntity = new HttpEntity(headers);
        ResponseEntity<ZhihuPager> response = restTemplate.exchange(builder.build().encode().toUri(),HttpMethod.GET, requestEntity, ZhihuPager.class);
        ZhihuPager<T> body = response.getBody();
        return body.getData();
    }

    @Override
    public People getPeople(String user_token) {
        String url = "https://www.zhihu.com/api/v4/members/" + user_token + "?include=locations,employments,gender,educations,business,voteup_count,thanked_Count,follower_count,following_count,cover_url,following_topic_count,following_question_count,following_favlists_count,following_columns_count,avatar_hue,answer_count,articles_count,pins_count,question_count,columns_count,commercial_question_count,favorite_count,favorited_count,logs_count,marked_answers_count,marked_answers_text,message_thread_token,account_status,is_active,is_bind_phone,is_force_renamed,is_bind_sina,is_privacy_protected,sina_weibo_url,sina_weibo_name,show_sina_weibo,is_blocking,is_blocked,is_following,is_followed,mutual_followees_count,vote_to_count,vote_from_count,thank_to_count,thank_from_count,thanked_count,description,hosted_live_count,participated_live_count,allow_message,industry_category,org_name,org_homepage,badge[?(type=best_answerer)].topics";
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
        headers.add("authorization", "oauth c3cef7c66a1843f8b3a9e6a1e3160e20");
        HttpEntity requestEntity = new HttpEntity(headers);
        ResponseEntity<People> response = restTemplate.exchange(url,HttpMethod.GET, requestEntity, People.class);
        return response.getBody();
    }
}

package ren.superk.zhihu.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ren.superk.zhihu.core.ZhihuEnum;
import ren.superk.zhihu.model.People;
import ren.superk.zhihu.repository.PeopleRepository;
import ren.superk.zhihu.service.PeopleUrlService;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
public class PeopleAction {

//    @Autowired
//    private TransportClient client;
    @Autowired
    private PeopleUrlService peopleUrlService;
    @Autowired
    private PeopleRepository peopleRepository;

    @RequestMapping("/people/{id}")
    public Optional<People> get(@PathVariable String id){
//        GetResponse response = client.prepareGet("zhihu", "people", id).get();
        return peopleRepository.findById(id);
    }
    @RequestMapping("/url/people/{id}")
    public People getUrl(@PathVariable String id){
        return peopleUrlService.getPeople(id);
    }
    @RequestMapping("/url/people/{id}/{type}")
    public List<Map> getollowees(@PathVariable String id
            , @RequestParam(required = false) Integer offset
            , @RequestParam(required = false) Integer limit
            , @PathVariable String type){

        return peopleUrlService.findList(id,offset,limit, ZhihuEnum.byVal(type),Map.class);
    }


}

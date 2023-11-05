package com.example.shinnytest.repository;

import com.example.shinnytest.entity.PermissonEntity;
import com.example.shinnytest.entity.State;
import com.example.shinnytest.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class PermissonRepositoryTest {
    @Autowired
    private PermissonRepository dataRepository;
    @Autowired
    private UserRepository userRepository;


    @Test
    public void saveData(){
        State state = new State(true,false,true);
        String [] dataCanhBao = {"Ho so den ki dieu tri","Danh sach hen trong ngay","Don den thanh toan"};

        for(int i=0; i< dataCanhBao.length; i++){
            PermissonEntity data = new PermissonEntity();
            data.setState(state);
            data.setText(dataCanhBao[i]);
            data.setParent(dataRepository.findById(1));
            dataRepository.save(data);
        }

    }

    @Test
    public void addChildren(){
        PermissonEntity permissonEntity = dataRepository.findById(1);
        List<PermissonEntity> list = new ArrayList<>();
        list.add(dataRepository.findById(4));
        list.add(dataRepository.findById(5));
        list.add(dataRepository.findById(6));
        permissonEntity.setChildren(list);
        dataRepository.save(permissonEntity);
    }
    @Test
    public void addUser(){
        User user = new User();
        user.setName("test1");
        userRepository.save(user);
        User user1 = userRepository.findById(1);
        PermissonEntity permisson = dataRepository.findById(1);
        user1.addPermissons(permisson);
        userRepository.save(user1);

    }

}
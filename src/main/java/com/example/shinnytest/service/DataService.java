package com.example.shinnytest.service;

import com.example.shinnytest.Model.*;
import com.example.shinnytest.entity.*;
import com.example.shinnytest.repository.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@org.springframework.stereotype.Service
public class DataService {
    @Autowired
    private CapitalRepository capitalRepository;
    @Autowired
    private InterestRepository interestRepository;
    @Autowired
    private RateRepository rateRepository;
    @Autowired
    private TypeCapitalRepository typeCapitalRepository;
    @Autowired
    private WalletRepository walletRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private MediaRepository mediaRepository;
    @Autowired
    private PermissonRepository dataRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PositionRepository positionRepository;
    @Autowired
    private StatusRepository statusRepository;
    @Autowired
    private DepartmentRepository departmentRepository;
    @Autowired
    private CampRepository campRepository;
    public Response getAdmin() {
        List<PermissonEntity> listDataAll = dataRepository.findAll();
        List<PermissonEntity> fillterList = new ArrayList<>();
        for(PermissonEntity entity : listDataAll){
            if(entity.getParent()==null){
                fillterList.add(entity);
            }
        }
        return new Response("thanh cong ",fillterList);
    }

    public Response addPermisson(AddPermissonId arrayList) {
        List<PermissonEntity> listAdd = new ArrayList<>();
        for (long i : arrayList.getIds()){
            PermissonEntity permissonAdd = dataRepository.findById(i);
            permissonAdd.getState().setSelected(true);
            listAdd.add(permissonAdd);
            PermissonEntity permissonParent = permissonAdd.getParent();
            permissonParent.setChildren(listAdd);
//            permissonParent.addChildren(permissonAdd);
        }
       Response list =getAdmin();
        return list;
    }

    public ResponseUser getPermissonByStaffId(long id) {
        User user = userRepository.findById(id);
        List<PermissonEntity> list = user.getPermissons();
        if(user == null){
            return new ResponseUser("User khong ton tai");
        }
        return new ResponseUser("Thanh cong",list);

    }

    public ResponseUser addPermissonByStaffId(AddPermissonId addPermissonId) {
        User user = userRepository.findById(addPermissonId.getUser_id());

        List<Long> listNewPermissons = addPermissonId.getIds();
            user.setPermissons(new ArrayList<>());
            for (long i : listNewPermissons){
                PermissonEntity permisson = dataRepository.findById(i);
                user.addPermissons(permisson);
            }

        userRepository.save(user);
        List<PermissonEntity> listPermissons = user.getPermissons();
        return new ResponseUser("thanh cong ",listPermissons);
    }

    public ResponseSearch searchUser(SearchRequest searchRequest) {
        String username = searchRequest.getUserName();
        Long departmentiId = searchRequest.getDepartmentId();
        Long status = searchRequest.getStatus();
        Long positionId = searchRequest.getPostionId();
        List<User> list = userRepository.listAll(status,positionId,departmentiId,username);
        return new ResponseSearch("thanh cong",list);

    }

    public ResponseAddUser addUser(InUser inUser) {
        String userName = inUser.getUserName();
        String passWord = inUser.getPassword();
        String fullName = inUser.getFullName();
        String phone = inUser.getPhone();
        String email = inUser.getEmail();
        long departmentId = inUser.getDepartmentId();
        long positionId = inUser.getPositionId();
        long statusId = inUser.getStatus();
        long managerId = inUser.getManagerId();
        User user = new User();
        user.setFullName(fullName);
        user.setName(userName);
        user.setPassWord(passWord);
        user.setPhone(phone);
        user.setEmail(email);
        Department department = departmentRepository.findById(departmentId);
        user.setDepartment(department);
        user.setDepartmentName(department.getDepartmentName());
        Position position = positionRepository.findById(positionId);
        user.setPosition(position);
        user.setPositionName(position.getPositionName());
        Status status = statusRepository.findById(statusId);
        user.setStatus(status);
        user.setStatusName(status.getStatusName());
        User userManager = userRepository.findById(managerId);
        user.setManagers(userManager);
        userRepository.save(user);
        List<User> userList = userRepository.findAll();
        return new ResponseAddUser("thanh cong",userList);
    }

    public void deleteUser(long id) {
        User user = userRepository.findById(id);
        if(user!= null){
            userRepository.deleteById(id);
        }
        else {
            System.out.println(" user khong ton tai");
        }
    }

    public ResponseUpdate changInformation(long id, InUser user) {
        User userUpdate = userRepository.findById(id);
        userUpdate.setPhone(user.getPhone());
        userUpdate.setEmail(user.getEmail());
        userUpdate.setFullName(user.getFullName());
        User user1 = userRepository.save(userUpdate);
        return new ResponseUpdate("thanh cong",user1);
    }

    public Response addCamp(RequestCamp requestCamp) {
        Camp camp = new Camp();
        camp.setNameCamp(requestCamp.getName());
        camp.setCode(requestCamp.getCode());
        camp.setStatus(requestCamp.getStatus());
        camp.setCreated_at(new Date());
        campRepository.save(camp);

        return  new Response("add camp thanh cong");
    }

    public Response addMedia(InMedia inMedia) {
            Media media = new Media();
            media.setGhi_chu(inMedia.getGhi_chu());
            media.setLink(inMedia.getLink());
            media.setMa_nguon(inMedia.getMa_nguon());
            User user_giaocho = userRepository.findById(inMedia.getGiao_cho());
            Camp camp = campRepository.findById(inMedia.getCamp_id());
            if(user_giaocho !=null && camp !=null){
                media.setGiao_cho(user_giaocho);
                media.setCamp(camp);
                mediaRepository.save(media);
                return new Response("Tao media thanh cong ");

            }
            else {
                return new Response("Khong ton tai nguoi nhan viec");
            }

    }

    public ResponseCamp getCamp() {
        List<Camp> campList = campRepository.findAll();
        return new ResponseCamp("Thanh cong",campList);
    }

    public Response addProduct(AddProduct product) {
        Product newProduct = new Product();
        newProduct.setAmount(product.getAmount());
        newProduct.setName(product.getName());
        newProduct.setCode(product.getCode());
        newProduct.setCreatedAt(new Date());
        productRepository.save(newProduct);
        return new Response("addProduct thanh cong");

    }

    public Response addCapital(InCapital inCapital) {
        Capital capital = new Capital();
        capital.setMoney(inCapital.getMoney());
        capital.setDonated_date(new Date());
        capital.setPhone(inCapital.getPhone());
        capital.setFull_name(inCapital.getFullName());
        capital.setRate(inCapital.getRate());
        capital.setInterestBefore(false);
        capital.setPeriodFee(inCapital.getPeriodFee());
        capital.setTime_borrow(inCapital.getTimeBorrow());
        Wallet wallet = walletRepository.findById(inCapital.getWallet_id());
        capital.setWallet(wallet);
        capital.setTotalInterestPaid(0);
        capital.setTotalMoneyCurrent(inCapital.getMoney());
        capital.setWalletName(wallet.getWallet_name());
        Interest interest = interestRepository.findById(inCapital.getTypeInterest());
        Rate rate = rateRepository.findById(inCapital.getTypeRate());
        TypeCapital typeCapital = typeCapitalRepository.findById(inCapital.getTypeCapital());
        capital.setInterestType(interest);
        capital.setTypeRate(rate);
        capital.setTypeRateName(rate.getType_rate());
        capital.setTypeCapital(typeCapital);
        capital.setTypeCapitalName(typeCapital.getTypeCapitalName());
        capitalRepository.save(capital);
        return new Response<>("thanh cong");
    }
    private void createSchedule(Capital capital){
        long kihan = capital.getTime_borrow()/capital.getPeriodFee();
        for(int i=1; i<= kihan; i++){
            PaymentSchedule paymentSchedule = new PaymentSchedule();
            paymentSchedule.setDone(false);
            paymentSchedule.setCountDate(capital.getPeriodFee());
            paymentSchedule.setPayNeed(1L);
        }
    }
}

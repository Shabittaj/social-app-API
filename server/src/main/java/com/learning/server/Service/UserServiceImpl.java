package com.learning.server.Service;

import com.learning.server.Model.Content;
import com.learning.server.Model.User;
import com.learning.server.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Override
    public User registeruser(User user) throws Exception {
        User olduser = userRepository.findByEmail(user.getEmail());
        if(olduser!=null){
           throw new Exception("email already exists with emailId: "+user.getEmail());
        }

        return userRepository.save(user);
    }

    @Override
    public List<User> fetchUser() {
        return userRepository.findAll();
    }

    @Override
    public User findByUserId(Integer userId) throws Exception {
        Optional<User> user= userRepository.findById(userId);
        if(user.isPresent()){
                return user.get();
        }
        throw new Exception("user does not exists with userId: "+userId);
    }

    @Override
    public User findByUserEmail(String email) throws Exception {
        User user = userRepository.findByEmail(email);
        return user;
    }

    @Override
    public User updateUserDetails(User user, Integer userId) throws Exception {
        Optional<User> findUser = userRepository.findById(userId);
        if(findUser.isEmpty()){
            throw new Exception("user does not exists with userId: "+userId);
        }
        User oldUser = findUser.get();
        if(user.getFirstName()!=null){
            oldUser.setFirstName(user.getFirstName());
        }
        if(user.getLastName()!=null){
            oldUser.setLastName(user.getLastName());
        }
        if(user.getEmail()!=null){
            oldUser.setEmail(user.getEmail());
        }
        if(user.getGender()!=null){
            oldUser.setGender(user.getGender());
        }
        return userRepository.save(oldUser);
    }

    @Override
    public String deleteByUserid(Integer userId) throws Exception {
        Optional<User> user = userRepository.findById(userId);
        if(user.isEmpty()){
            throw new Exception("user does not exists with userId: "+userId);
        }
        userRepository.deleteById(userId);
        return "User deleted successfully with userId: "+userId;

    }

    @Override
    public List<User> searchUser(String query) {
        return userRepository.searchUser(query);
    }

    @Override
    public User followUser(Integer id1, Integer id2) throws Exception {
        User user1= findByUserId(id1);
        User user2= findByUserId(id2);

//        user1.getFollowings().add(user2.getId());
//        user2.getFollowers().add(user1.getId());
        if(user1.getFollowings().contains(user2.getId())){
            user1.getFollowings().remove(user2.getId());
            user2.getFollowers().remove(user1.getId());
        }else{
            user1.getFollowings().add(user2.getId());
            user2.getFollowers().add(user1.getId());
        }

        userRepository.save(user1);
        userRepository.save(user2);
        return user1;
    }

    @Override
    public List<Content> savedContent(Integer userId) {
        List<Content> savedContent = userRepository.findContentByUserId(userId);
        return savedContent;
    }
}

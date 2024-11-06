package com.project.public_safety_app.service;

import com.project.public_safety_app.dto.EmergencyContactDto;
import com.project.public_safety_app.dto.UserDto;

import com.project.public_safety_app.dto.UserResponse;
import com.project.public_safety_app.exceptions.InvalidCredentialsException;
import com.project.public_safety_app.exceptions.UserNotFoundException;
import com.project.public_safety_app.model.*;
import com.project.public_safety_app.repository.*;
import com.project.public_safety_app.util.EntityUtil;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private EmergencyContactRepository emergencyContactRepository;

    @Autowired
    private EmergencyContactService emergencyContactService;

    @Autowired
    private QuizRepository quizRepository;

    @Autowired
    private SafetyCheckInRepository safetyCheckInRepository;

    @Autowired
    private SafetyReportRepository safetyReportRepository;

    @Autowired
    private DiscussionRepository discussionRepository;



    private BCryptPasswordEncoder passwordEncoder=new BCryptPasswordEncoder();

    public UserDto createUser(UserDto userDto) {
        User user = EntityUtil.convertToEntity(userDto);
        // Do not encode the password

        user.setPassword(passwordEncoder.encode(userDto.getPassword()));// Not secure
//        for(EmergencyContacts i: user.getContacts()){
//            i.setUser(user);
//            emergencyContactService.saveContacts(EntityUtil.convertECToDTO(i));
//        }
        User savedUser = userRepository.save(user);
        for(EmergencyContacts i: user.getContacts()){
            i.setUser(user);
            emergencyContactRepository.save(i);
        }

        // Save user (contacts should not be saved again)
//        User savedUser = userRepository.save(user);

        // Save contacts separately if necessary

        user.setDiscussions(null);
        user.setSafetyReports(null);
        user.setQuizzes(null);
        user.setSafetyCheckIns(null);
//        User savedUser = userRepository.save(user);
        return EntityUtil.convertToDTO(savedUser);
    }

    public UserDto login(String userName, String password) {
        User user = userRepository.findByUserName(userName);
        if (user != null && passwordEncoder.matches(password,user.getPassword())) { // Not secure
            return EntityUtil.convertToDTO(user);
        } else {
            throw new InvalidCredentialsException("Invalid username or password");
        }
    }

    public UserDto updateUser(Long userId, UserDto userDto) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.setUserName(userDto.getUserName());
            user.setEmail(userDto.getEmail());
            user.setDOB(userDto.getDOB());
            user.setUserPhoneNumber(userDto.getUserPhoneNumber());
            if (userDto.getPassword() != null && !userDto.getPassword().isEmpty()) {
                user.setPassword(userDto.getPassword()); // Not secure
            }
            User updatedUser = userRepository.save(user);
            return EntityUtil.convertToDTO(updatedUser);
        } else {
            throw new UserNotFoundException("User not found with ID: " + userId);
        }
    }

    public void deleteUser(Long userId) {
        if (userRepository.existsById(userId)) {
            userRepository.deleteById(userId);
        } else {
            throw new UserNotFoundException("User not found with ID: " + userId);
        }
    }

    public UserDto getUserByName(String name) {
        User user = userRepository.findByUserName(name);
        if (user == null) {
            throw new UserNotFoundException("User not found with username: " + name);
        }
        UserResponse userResponse=getUser(name);
        userResponse.addLinks(name);
        return EntityUtil.convertToDTO(user);
    }

    public void deleteUserByName(String name) {
        User user = userRepository.findByUserName(name);
        if (user != null) {
            List<Quiz> quizzes = user.getQuizzes();
            for (Quiz quiz : quizzes) {
                quiz.setUser(null); // Remove the user reference
                // Save the quiz without the user reference
                quizRepository.save(quiz);
                quizRepository.deleteById(quiz.getId());
            }
            List<SafetyCheckIn> safetyCheckIns = user.getSafetyCheckIns();
            for (SafetyCheckIn safetyCheckIn : safetyCheckIns) {
                safetyCheckIn.setUser(null); // Remove the user reference
                // Save the quiz without the user reference
                safetyCheckInRepository.save(safetyCheckIn);
                safetyCheckInRepository.deleteById(safetyCheckIn.getId());
            }
            List<SafetyReport> safetyReports = user.getSafetyReports();
            for (SafetyReport safetyReport : safetyReports) {
                safetyReport.setUser(null); // Remove the user reference
                // Save the quiz without the user reference
                safetyReportRepository.save(safetyReport);
                safetyReportRepository.deleteById(safetyReport.getId());
            }
            List<Discussion> discussions = user.getDiscussions();
            for (Discussion discussion : discussions) {
                discussion.setUser(null); // Remove the user reference
                // Save the quiz without the user reference
                discussionRepository.save(discussion);
                discussionRepository.deleteById(discussion.getId());
            }
            userRepository.deleteById(user.getUserId());
        }
    }

    public List<UserDto> getAllUsers() {
//        return userRepository.findAll().stream().map(EntityUtil::convertToDTO).toList();
        List<User> users = userRepository.findAll();

        // Map each user to a UserResponse and add HATEOAS links
        return users.stream().map(user -> {
            UserResponse userResponse = new UserResponse();

            // Populate UserResponse with user details
            userResponse.setUserId(user.getUserId());
            userResponse.setUserName(user.getUserName());
            userResponse.setEmail(user.getEmail());
            userResponse.setUserPhoneNumber(user.getUserPhoneNumber());
            userResponse.setPassword(user.getPassword());  // Note: You might want to omit this field for security reasons.
            userResponse.setContacts(user.getContacts());
            userResponse.setDiscussions(user.getDiscussions());
            userResponse.setSafetyCheckIns(user.getSafetyCheckIns());
            userResponse.setSafetyReports(user.getSafetyReports());
            userResponse.setQuizzes(user.getQuizzes());
            userResponse.setDOB(user.getDOB());

            // Add HATEOAS links for each user
            userResponse.addLinks(user.getUserName());

            return userResponse;
        }).collect(Collectors.toList());

    }

    @Override
    public List<EmergencyContactDto> getUserContactsByUsername(String username) {

        User user = userRepository.findByUserName(username);
        if (user == null) {
            throw new UserNotFoundException("User not found with username: " + username);
        }

        List<EmergencyContacts> contacts = emergencyContactService.findByUser(user).stream().map(EntityUtil::convertSTOToECT).toList();

        return contacts.stream().map(EntityUtil::convertECToDTO).collect(Collectors.toList());
    }

    public List<Quiz> getQuizBYUserName(String userName){
        User user=userRepository.findByUserName(userName);
        return user.getQuizzes();
    }

    public UserResponse getUser(String userName) {


        // Map the User entity to UserResponse DTO
        User user = userRepository.findByUserName(userName);
        UserResponse userResponse = new UserResponse();
//
        userResponse.setUserId(user.getUserId());
        userResponse.setUserName(user.getUserName());
        userResponse.setEmail(user.getEmail());
        userResponse.setUserPhoneNumber(user.getUserPhoneNumber());
        userResponse.setPassword(user.getPassword());
        List<EmergencyContacts> contacts=user.getContacts();
        userResponse.setContacts(contacts);
        userResponse.setDiscussions(user.getDiscussions());
        userResponse.setSafetyCheckIns(user.getSafetyCheckIns());
        userResponse.setSafetyReports(user.getSafetyReports());
        userResponse.setQuizzes(user.getQuizzes());
        userResponse.setDOB(user.getDOB());

        // Add HATEOAS links for related resources
        userResponse.addLinks(userName);

        return userResponse;
    }






}


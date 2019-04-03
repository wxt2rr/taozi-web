package code.dao;

import code.pojo.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface UserDao {

    User checkUser(@Param("account") String account, @Param("password") String password);

    User getUserById(int userId);

    User getUserByPhone(String phone);

    void saveUser(User user);

    void uploadHead(@Param("userId") int userId, @Param("url") String imgFilePath,@Param("date") Date date);

    int updateInfo(@Param("userId") int userId,@Param("name") String name,@Param("job") String job,@Param("sex") int sex,@Param("aboutme") String aboutme);

    int attentionUser(@Param("userId") int userId,@Param("aUserId") int aUserId);

    int quitAttentionUser(@Param("userId") int userId,@Param("aUserId") int aUserId);

    List<Integer> getAttentionUser(int userId);

    List<User> getUserByIds(@Param("ids") List<Integer> attentionUser);

    int updatePassword(@Param("phone") String phone,@Param("password") String password);
}

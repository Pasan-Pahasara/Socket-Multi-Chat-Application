package lk.ijse.multiChatApplication.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author : Pasan Pahasara
 * @since : 0.1.0
 **/

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    public String name;
    public String password;
    public String email;
    public String gender;
    public String phoneNo;
}

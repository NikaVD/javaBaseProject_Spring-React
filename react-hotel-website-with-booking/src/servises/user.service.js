import axios from "axios";
import { BASE_API_URL } from "../common/constants";
import { authHeader } from "./base.service";

const API_URL = BASE_API_URL + "/api/admin";

class UserService {
  changeRole(role, user) {
    return axios.put(API_URL + "/change/" + role, user, {
      headers: authHeader(),
    });
  }

  getAllUsers() {
    return axios.get(API_URL + "/users", { headers: authHeader() });
  }

  deleteUser(user) {
    return axios.delete(API_URL + "/user/" + user.id, {
      headers: authHeader(),
    });
  }
}

export default new UserService();

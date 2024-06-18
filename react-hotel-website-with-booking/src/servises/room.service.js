import axios from "axios";
import { BASE_API_URL } from "../common/constants";
import { authHeader } from "./base.service";

const API_URL_ADMIN = BASE_API_URL + "/api/admin/room";
const API_URL_PUBLIC = BASE_API_URL + "/api/public/room";

class RoomService {
  saveRoom(room) {
    return axios.post(API_URL_ADMIN, room, { headers: authHeader() });
  }

  deleteRoom(room) {
    return axios.delete(API_URL_ADMIN + "/" + room.id, {
      headers: authHeader(),
    });
  }

  getAllRooms() {
    return axios.get(API_URL_PUBLIC);
  }

  getRoom(roomId) {
    return axios.get(API_URL_PUBLIC + "/" + roomId);
  }

  getAllAvailableRooms(startDate, endDate) {
    return axios.get(API_URL_PUBLIC + "/dates", {
      params: { dateFrom: startDate, dateTo: endDate },
    });
  }
}

export default new RoomService();

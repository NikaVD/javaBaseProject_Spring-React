import axios from "axios";
import { BASE_API_URL } from "../common/constants";
import { authHeader } from "./base.service";

const API_URL = BASE_API_URL + "/api/user/reservation";

class ReservationService {
  saveReservation(reservation) {
    return axios.post(API_URL, reservation, { headers: authHeader() });
  }

  getAllReservationsForUser() {
    return axios.get(API_URL, { headers: authHeader() });
  }

  deleteReservation(reservation) {
    return axios.delete(API_URL + "/" + reservation.id, {
      headers: authHeader(),
    });
  }
}

export default new ReservationService();

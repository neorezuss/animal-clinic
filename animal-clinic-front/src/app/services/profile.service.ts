import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {UserProfile} from "../classes/user-profile";

@Injectable({
  providedIn: 'root'
})
export class ProfileService {
  private profileControllerUrl: string = 'http://localhost:8080/api/v1/user-profile';

  constructor(private http: HttpClient) {
  }

  public getUserProfile(): Observable<UserProfile> {
    return this.http.get<UserProfile>(this.profileControllerUrl);
  }

  updateUserProfile(userProfile: UserProfile): Observable<UserProfile> {
    return this.http.post<UserProfile>(this.profileControllerUrl, userProfile)
  }
}

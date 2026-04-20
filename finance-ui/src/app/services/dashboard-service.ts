import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class DashboardService {
  private baseUrl = 'http://localhost:8080/api/dashboard'

  constructor(private httpClient: HttpClient) {
  }

  getDashboard(userId: number, year: number, month: number): Observable<any> {
    return this.httpClient.get(`${this.baseUrl}?userId=${userId}&year=${year}&month=${month}`)
  }
}

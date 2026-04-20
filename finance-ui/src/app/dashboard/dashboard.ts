import {Component, OnInit, signal} from '@angular/core';
import {DashboardService} from '../services/dashboard-service';

@Component({
  standalone: true,
  selector: 'app-dashboard',
  imports: [],
  templateUrl: './dashboard.html',
  styleUrl: './dashboard.scss',
})
export class Dashboard implements OnInit {

  data = signal<any>(null);

  constructor(private dashboardService: DashboardService) {
  }

  ngOnInit(): void {
    this.loadDashboard();
    console.info("Data loaded: ", this.data);
  }

  loadDashboard() {
    this.dashboardService.getDashboard(1, 2026, 4)
      .subscribe(res => {
        this.data.set(res);
        console.info("API returned data: ", res);
      });
  }

}

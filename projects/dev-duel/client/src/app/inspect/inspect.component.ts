import { Component, Input, OnInit } from '@angular/core';
import { UserService } from 'src/user.service';

@Component({
  selector: 'app-inspect',
  templateUrl: './inspect.component.html',
  styleUrls: ['./inspect.component.css'],
})
export class InspectComponent implements OnInit {
  username: string = '';
  profile: any = null;
  error: string | null = null;
  loading: boolean = false;

  constructor(private userService: UserService) {}

  ngOnInit(): void {}

  receiveUsername(valueEmitted: string) {
    this.username = valueEmitted;
  }

  async onSubmit() {
    if (!this.username.trim()) return;

    this.loading = true;
    this.error = null;
    this.profile = null;

    try {
      const data = await this.userService.inspectUser(this.username);
      this.profile = data;
    } catch (err: any) {
      this.error =
        err.error?.tips || err.error?.error || 'Failed to load user data.';
    } finally {
      this.loading = false;
    }
  }
}

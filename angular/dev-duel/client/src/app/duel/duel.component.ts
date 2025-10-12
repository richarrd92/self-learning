import { Component, OnInit } from '@angular/core';
import { UserService } from 'src/user.service';

@Component({
  selector: 'app-duel',
  templateUrl: './duel.component.html',
  styleUrls: ['./duel.component.css'],
})
export class DuelComponent implements OnInit {
  usernameOne: string = '';
  usernameTwo: string = '';

  profileOne: any = null;
  profileTwo: any = null;

  loading: boolean = false;
  error: string | null = null;
  winner: 'one' | 'two' | 'tie' | null = null;

  constructor(private userService: UserService) {}

  ngOnInit(): void {}

  receiveUsernameOne(valueEmitted: string) {
    this.usernameOne = valueEmitted;
  }

  receiveUsernameTwo(valueEmitted: string) {
    this.usernameTwo = valueEmitted;
  }

  async onSubmit() {
    if (!this.usernameOne.trim() || !this.usernameTwo.trim()) return;

    this.loading = true;
    this.error = null;
    this.profileOne = null;
    this.profileTwo = null;
    this.winner = null;

    try {
      // Force TypeScript to treat the result as an array
      const data = (await this.userService.duelUsers(
        this.usernameOne,
        this.usernameTwo
      )) as any[];

      if (!Array.isArray(data) || data.length < 2) {
        this.error = 'Could not retrieve both users.';
        return;
      }

      const [user1, user2] = data;

      this.profileOne = user1;
      this.profileTwo = user2;

      this.determineWinner();
    } catch (err: any) {
      this.error =
        err.error?.tips || err.error?.error || 'Failed to load user data.';
    } finally {
      this.loading = false;
    }
  }

  determineWinner() {
    const profileScores = (profile: any) => {
      let score = 0;

      // Weighted scoring
      score += (profile['total-stars'] || 0) * 3; // contribution impact
      score += (profile.followers || 0) * 2; // popularity
      score += (profile['public-repos'] || 0) * 1; // experience
      score += (profile['highest-starred'] || 0) * 2; // flagship project
      score += (profile['perfect-repos'] || 0) * 2; // quality

      // Bonus points for special titles
      if (profile.titles?.includes('Jack of all Trades')) score += 5;
      if (profile.titles?.includes('One-Trick Pony')) score += 2;
      if (profile.titles?.includes('Mr. Popular')) score += 3;
      if (profile.titles?.includes('Forker')) score += 1;
      if (profile.titles?.includes('Stalker')) score += 1;

      return score;
    };

    const scoreOne = profileScores(this.profileOne);
    const scoreTwo = profileScores(this.profileTwo);

    if (scoreOne > scoreTwo) this.winner = 'one';
    else if (scoreTwo > scoreOne) this.winner = 'two';
    else this.winner = 'tie';

    console.log('Scores:', { scoreOne, scoreTwo, winner: this.winner });
  }
}

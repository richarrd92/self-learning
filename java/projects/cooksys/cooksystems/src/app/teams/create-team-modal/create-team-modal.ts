import { Component, Inject, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { MatDialogRef, MatDialogModule, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { MatSelectModule } from '@angular/material/select';
import { TeamService, CreateTeamDto } from '../../services/team.service';
import { UserService, UserResponseDto } from '../../services/user.service';

interface DialogData {
  companyId: number;
  userId: number;
}

@Component({
  selector: 'app-create-team-modal',
  standalone: true,
  imports: [
    CommonModule,
    FormsModule,
    MatDialogModule,
    MatFormFieldModule,
    MatInputModule,
    MatButtonModule,
    MatSelectModule
  ],
  templateUrl: './create-team-modal.html',
})
export class CreateTeamModalComponent implements OnInit {
  teamName: string = '';
  description: string = '';
  selectedUserIds: number[] = [];
  availableUsers: UserResponseDto[] = [];
  errorMessage: string = '';
  isSubmitting: boolean = false;
  isLoadingUsers: boolean = true;

  constructor(
    private dialogRef: MatDialogRef<CreateTeamModalComponent>,
    private teamService: TeamService,
    private userService: UserService,
    @Inject(MAT_DIALOG_DATA) public data: DialogData
  ) {}

  ngOnInit(): void {
    this.loadUsers();
  }

  loadUsers(): void {
    this.isLoadingUsers = true;
    this.userService.getUsersByCompany(this.data.companyId).subscribe({
      next: (users) => {
        this.availableUsers = users.filter(user => user.userState.active);
        this.isLoadingUsers = false;
      },
      error: (error) => {
        console.error('Failed to load users:', error);
        this.errorMessage = 'Failed to load users. Please try again.';
        this.isLoadingUsers = false;
      }
    });
  }

  addMember(userId: number): void {
    if (!this.selectedUserIds.includes(userId)) {
      this.selectedUserIds.push(userId);
    }
  }

  removeMember(userId: number): void {
    this.selectedUserIds = this.selectedUserIds.filter(id => id !== userId);
  }

  getUserById(userId: number): UserResponseDto | undefined {
    return this.availableUsers.find(user => user.id === userId);
  }

  getAvailableUsersForDropdown(): UserResponseDto[] {
    return this.availableUsers.filter(user => !this.selectedUserIds.includes(user.id));
  }

  onSubmit(): void {
    this.errorMessage = '';

    if (!this.teamName || !this.description) {
      this.errorMessage = 'Please fill in both team name and description fields.';
      return;
    }

    if (this.selectedUserIds.length === 0) {
      this.errorMessage = 'Please select at least one team member.';
      return;
    }

    this.isSubmitting = true;

    const newTeam: CreateTeamDto = {
      authorId: this.data.userId,
      name: this.teamName,
      description: this.description,
      userIds: this.selectedUserIds
    };

    this.teamService.createTeam(this.data.companyId, newTeam).subscribe({
      next: (team) => {
        this.isSubmitting = false;
        this.dialogRef.close(true);
      },
      error: (error) => {
        console.error('Failed to create team:', error);
        this.errorMessage = 'Failed to create team. Please try again.';
        this.isSubmitting = false;
      }
    });
  }

  onClose(): void {
    this.dialogRef.close(false);
  }
}

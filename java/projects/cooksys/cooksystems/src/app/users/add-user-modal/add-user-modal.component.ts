import { Component, Inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { MatDialogRef, MatDialogModule, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { MatSelectModule } from '@angular/material/select';
import { UserService } from '../../services/user.service';
import { UserRequestDto } from '../../services/announcement.service';

interface DialogData {
  companyId: number;
}

@Component({
  selector: 'app-add-user-modal',
  standalone: true,
  imports: [
    CommonModule,
    FormsModule,
    MatDialogModule,
    MatFormFieldModule,
    MatInputModule,
    MatButtonModule,
    MatSelectModule,
  ],
  templateUrl: './add-user-modal.component.html',
})
export class AddUserModalComponent {
  firstName: string = '';
  lastName: string = '';
  email: string = '';
  phone: string = '';
  password: string = '';
  confirmPassword: string = '';
  isAdmin: boolean = false;
  errorMessage: string = '';
  isSubmitting: boolean = false;

  constructor(
    private dialogRef: MatDialogRef<AddUserModalComponent>,
    private userService: UserService,
    @Inject(MAT_DIALOG_DATA) public data: DialogData
  ) {}

  onSubmit(): void {
    this.errorMessage = '';

    // Validation
    if (
      !this.firstName ||
      !this.lastName ||
      !this.email ||
      !this.phone ||
      !this.password ||
      !this.confirmPassword
    ) {
      this.errorMessage = 'Please fill in all fields.';
      return;
    }

    if (this.password !== this.confirmPassword) {
      this.errorMessage = 'Passwords do not match.';
      return;
    }

    // Validate phone has at least 10 digits
    const phoneDigits = this.phone.replace(/\D/g, '');
    if (phoneDigits.length < 10) {
      this.errorMessage = 'Phone number must have at least 10 digits.';
      return;
    }

    this.isSubmitting = true;

    // Generate username: firstname + lastname + last 4 digits of phone
    const last4Digits = phoneDigits.slice(-4);
    const username = `${this.firstName}${this.lastName}${last4Digits}`;

    const newUser: UserRequestDto = {
      credentials: {
        username: username,
        password: this.password,
        email: this.email,
      },
      profile: {
        first: this.firstName,
        last: this.lastName,
        phone: this.phone,
      },
      userState: {
        active: true,
        admin: this.isAdmin,
        status: 'PENDING',
      },
    };

    // Get companyId from localStorage
    const companyIdStr = localStorage.getItem('selectedCompanyId');
    if (!companyIdStr) {
      this.errorMessage = 'No company selected. Please select a company first.';
      this.isSubmitting = false;
      return;
    }
    const companyId = parseInt(companyIdStr, 10);

    this.userService.createUser(newUser, companyId).subscribe({
      next: (user) => {
        this.isSubmitting = false;
        this.dialogRef.close(true);
      },
      error: (error) => {
        console.error('Failed to create user:', error);
        this.errorMessage = 'Failed to create user. Please try again.';
        this.isSubmitting = false;
      },
    });
  }

  onClose(): void {
    this.dialogRef.close(false);
  }
}

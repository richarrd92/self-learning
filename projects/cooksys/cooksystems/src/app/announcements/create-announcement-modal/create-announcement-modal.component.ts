import { Component, Inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { MatDialogRef, MatDialogModule, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { AnnouncementService, CreateAnnouncementDto } from '../../services/announcement.service';

interface DialogData {
  companyId: number;
  userId: number;
}

@Component({
  selector: 'app-create-announcement-modal',
  standalone: true,
  imports: [
    CommonModule,
    FormsModule,
    MatDialogModule,
    MatFormFieldModule,
    MatInputModule,
    MatButtonModule
  ],
  templateUrl: './create-announcement-modal.component.html'
})
export class CreateAnnouncementModalComponent {
  title: string = '';
  message: string = '';
  errorMessage: string = '';
  isSubmitting: boolean = false;

  constructor(
    private dialogRef: MatDialogRef<CreateAnnouncementModalComponent>,
    private announcementService: AnnouncementService,
    @Inject(MAT_DIALOG_DATA) public data: DialogData
  ) {}

  onSubmit(): void {
    this.errorMessage = '';

    if (!this.title || !this.message) {
      this.errorMessage = 'Please fill in both title and message fields.';
      return;
    }

    this.isSubmitting = true;

    const newAnnouncement: CreateAnnouncementDto = {
      authorId: this.data.userId,
      title: this.title,
      message: this.message
    };

    this.announcementService.createAnnouncement(this.data.companyId, newAnnouncement).subscribe({
      next: (announcement) => {
        this.isSubmitting = false;
        this.dialogRef.close(true);
      },
      error: (error) => {
        console.error('Failed to create announcement:', error);
        this.errorMessage = 'Failed to create announcement. Please try again.';
        this.isSubmitting = false;
      }
    });
  }

  onClose(): void {
    this.dialogRef.close(false);
  }
}

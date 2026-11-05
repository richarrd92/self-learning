import { Component, Inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { MatDialogRef, MatDialogModule, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { MatSelectModule } from '@angular/material/select';
import { ProjectService, CreateProjectDto } from '../../services/project.service';

interface DialogData {
  companyId: number;
  teamId: number;
  userId: number;
}

@Component({
  selector: 'app-create-project-modal',
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
  templateUrl: './create-project-modal.html',
})
export class CreateProjectModalComponent {
  projectName: string = '';
  description: string = '';
  active: boolean = true;
  errorMessage: string = '';
  isSubmitting: boolean = false;

  constructor(
    private dialogRef: MatDialogRef<CreateProjectModalComponent>,
    private projectService: ProjectService,
    @Inject(MAT_DIALOG_DATA) public data: DialogData
  ) {}

  onSubmit(): void {
    this.errorMessage = '';

    if (!this.projectName || !this.description) {
      this.errorMessage = 'Please fill in both project name and description fields.';
      return;
    }

    this.isSubmitting = true;

    const newProject: CreateProjectDto = {
      name: this.projectName,
      description: this.description,
      active: this.active
    };

    this.projectService.createProject(this.data.companyId, this.data.teamId, newProject).subscribe({
      next: (project) => {
        this.isSubmitting = false;
        this.dialogRef.close(true);
      },
      error: (error) => {
        console.error('Failed to create project:', error);
        this.errorMessage = 'Failed to create project. Please try again.';
        this.isSubmitting = false;
      }
    });
  }

  onClose(): void {
    this.dialogRef.close(false);
  }
}

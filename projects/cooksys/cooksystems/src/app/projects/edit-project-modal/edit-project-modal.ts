import { Component, Inject, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { MatDialogRef, MatDialogModule, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { MatSelectModule } from '@angular/material/select';
import { ProjectService, ProjectDto, UpdateProjectDto } from '../../services/project.service';

interface DialogData {
  companyId: number;
  teamId: number;
  project: ProjectDto;
  userId: number;
  isAdmin: boolean;
}

@Component({
  selector: 'app-edit-project-modal',
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
  templateUrl: './edit-project-modal.html',
})
export class EditProjectModalComponent implements OnInit {
  projectName: string = '';
  description: string = '';
  active: boolean = true;
  errorMessage: string = '';
  isSubmitting: boolean = false;
  isAdmin: boolean = false;

  constructor(
    private dialogRef: MatDialogRef<EditProjectModalComponent>,
    private projectService: ProjectService,
    @Inject(MAT_DIALOG_DATA) public data: DialogData
  ) {}

  ngOnInit(): void {
    // Pre-populate form with existing project data
    this.projectName = this.data.project.name;
    this.description = this.data.project.description;
    this.active = this.data.project.active;
    this.isAdmin = this.data.isAdmin;
  }

  onSubmit(): void {
    this.errorMessage = '';

    if (!this.projectName || !this.description) {
      this.errorMessage = 'Please fill in both project name and description fields.';
      return;
    }

    this.isSubmitting = true;

    const updatedProject: UpdateProjectDto = {
      name: this.projectName,
      description: this.description,
      active: this.active
    };

    this.projectService.updateProject(this.data.companyId, this.data.teamId, this.data.project.id, updatedProject).subscribe({
      next: (project) => {
        this.isSubmitting = false;
        this.dialogRef.close(true);
      },
      error: (error) => {
        console.error('Failed to update project:', error);
        this.errorMessage = 'Failed to update project. Please try again.';
        this.isSubmitting = false;
      }
    });
  }

  onClose(): void {
    this.dialogRef.close(false);
  }
}

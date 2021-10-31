import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EditarUsuarioAdminComponent } from './editar-usuario-admin.component';

describe('EditarUsuarioAdminComponent', () => {
  let component: EditarUsuarioAdminComponent;
  let fixture: ComponentFixture<EditarUsuarioAdminComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ EditarUsuarioAdminComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(EditarUsuarioAdminComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

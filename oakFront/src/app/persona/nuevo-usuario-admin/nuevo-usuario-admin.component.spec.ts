import { ComponentFixture, TestBed } from '@angular/core/testing';

import { NuevoUsuarioAdminComponent } from './nuevo-usuario-admin.component';

describe('NuevoUsuarioAdminComponent', () => {
  let component: NuevoUsuarioAdminComponent;
  let fixture: ComponentFixture<NuevoUsuarioAdminComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ NuevoUsuarioAdminComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(NuevoUsuarioAdminComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

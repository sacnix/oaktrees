import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EditarCategoriaAdminComponent } from './editar-categoria-admin.component';

describe('EditarCategoriaAdminComponent', () => {
  let component: EditarCategoriaAdminComponent;
  let fixture: ComponentFixture<EditarCategoriaAdminComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ EditarCategoriaAdminComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(EditarCategoriaAdminComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

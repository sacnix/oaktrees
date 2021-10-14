import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DetalleRolComponent } from './detalle-rol.component';

describe('DetalleRolComponent', () => {
  let component: DetalleRolComponent;
  let fixture: ComponentFixture<DetalleRolComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DetalleRolComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(DetalleRolComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});

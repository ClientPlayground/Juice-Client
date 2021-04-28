package clientname.cosmetics.impl.eyes;


import clientname.cosmetics.CosmeticModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.entity.Entity;

public class ModelEyes extends CosmeticModelBase {

	private ModelRenderer[] iris = new ModelRenderer[12];
	private ModelRenderer[] pupils = new ModelRenderer[6];
	
	private final float POP1 = 0.5235987755982988F;
	private final float POP2 = 1.0471975511965976F;
	private final float POP3 = 1.5707963267948966F;
	private final float POP4 = 2.0943951023931953F;
	private final float POP5 = 2.6179938779914944F;
	private final float POP6 = 3.141592653589793F;
	
	public ModelEyes(RenderPlayer player) {
		super(player);
		
		for(int i = 0; i < iris.length; i++) {
			this.iris[i] = new ModelRenderer(this, 0, 0);
			this.iris[i].setRotationPoint(0, 0, 0);
			this.iris[i].addBox(-0.5F, -1.88F, -1.0F, 1, 3, 1, 0.0F);
		}
		
		this.setRotationAngle(iris[0], 0.0F, 0.0F, -POP1);
		this.setRotationAngle(iris[1], 0.0F, 0.0F, -POP2);
		this.setRotationAngle(iris[2], 0.0F, 0.0F, -POP3);
		this.setRotationAngle(iris[3], 0.0F, 0.0F, -POP4);
		this.setRotationAngle(iris[4], 0.0F, 0.0F, -POP5);
		this.setRotationAngle(iris[5], 0.0F, 0.0F, -POP6);
		
		this.setRotationAngle(iris[6], 0.0F, 0.0F, POP5);
		this.setRotationAngle(iris[7], 0.0F, 0.0F, POP4);
		this.setRotationAngle(iris[8], 0.0F, 0.0F, POP3);
		this.setRotationAngle(iris[9], 0.0F, 0.0F, POP2);
		this.setRotationAngle(iris[10], 0.0F, 0.0F, POP1);
		
		for(int i = 0; i < pupils.length; i++) {
			this.pupils[i] = new ModelRenderer(this, 0, 0);
			this.pupils[i].setRotationPoint(0, 0, 0);
			this.pupils[i].addBox(-0.5f, -0.88f, -1.5f, 1, 1, 1, 0.0F);
		}
		
		this.setRotationAngle(pupils[0], 0.0F, 0.0F, -POP2);
		this.setRotationAngle(pupils[1], 0.0F, 0.0F, -POP4);
		this.setRotationAngle(pupils[2], 0.0F, 0.0F, -POP6);
		this.setRotationAngle(pupils[3], 0.0F, 0.0F, POP4);
		this.setRotationAngle(pupils[4], 0.0F, 0.0F, POP2);		
	}

	private void setRotationAngle(ModelRenderer m, float x, float y, float z) {
		m.rotateAngleX = x;
		m.rotateAngleY = y;
		m.rotateAngleZ = z;
	}
	
	@Override
	public void render(Entity entityIn, float p_78088_2_, float p_78088_3_, float p_78088_4_, float p_78088_5_, float p_78088_6_, float scale) {
		/*Nothing here*/
	}
	
	public void renderIris(float pt) {
		for(int i = 0; i < iris.length; i++) {
			this.iris[i].render(pt);
		}
	}
	
	public void renderPupil(float pt) {
		for(int i = 0; i < pupils.length; i++) {
			this.pupils[i].render(pt);
		}
	}
	
	public void movePupil(float x, float y, float size) {
		
		float shiftFactor = (1.45F - size * 0.525F) / size;
		
		for(int i = 0; i < pupils.length; i++) {
			
			pupils[i].rotationPointX = -x * shiftFactor;
			pupils[i].rotationPointY = -y * shiftFactor * (float)Math.cos(Math.toRadians((x / 1F) * 90F));
			
		}
		
	}

}

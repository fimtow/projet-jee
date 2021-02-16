package ma.ensias.dao;

import ma.ensias.beans.Comment;

public class CommentDaoImpl implements CommentDao{

	private DAOFactory daoFactory;
	CommentDaoImpl(DAOFactory daoFactory)
	{
		this.daoFactory = daoFactory;
	}
	@Override
	public void create(Comment comment) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Comment find(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(Object... fields) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Comment comment) {
		// TODO Auto-generated method stub
		
	}

}

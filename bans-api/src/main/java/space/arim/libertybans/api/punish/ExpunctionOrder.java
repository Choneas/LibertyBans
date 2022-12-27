/*
 * LibertyBans
 * Copyright © 2022 Anand Beh
 *
 * LibertyBans is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * LibertyBans is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with LibertyBans. If not, see <https://www.gnu.org/licenses/>
 * and navigate to version 3 of the GNU Affero General Public License.
 */

package space.arim.libertybans.api.punish;

import space.arim.omnibus.util.concurrent.ReactionStage;

/**
 * An order to completely delete a punishment without any trace leftover. Obtained from {@link PunishmentRevoker}
 *
 */
public interface ExpunctionOrder extends EnforcementOptionsFactory {

	/**
	 * Gets the ID of the punishment which will be expunged
	 *
	 * @return the ID
	 */
	long getID();

	/**
	 * Expunges the punishment. <br>
	 * <br>
	 * An expunged punishment is not "undone" and therefore does not trigger undo notifications.
	 *
	 * @return a future which yields true if the punishment existed and was expunged
	 */
	default ReactionStage<Boolean> expunge() {
		return expunge(enforcementOptionsBuilder().build());
	}

	/**
	 * Expunges the punishment. <br>
	 * <br>
	 * An expunged punishment is not "undone" and therefore does not trigger undo notifications.
	 * As such, the {@link EnforcementOptions#broadcasting()} setting has no meaning here.
	 *
	 * @param enforcementOptions the enforcement options. Can be used to disable unenforcement entirely.
	 *                           The "broadcasting" setting has no effect.
	 * @return a future which yields true if the punishment existed and was expunged
	 */
	ReactionStage<Boolean> expunge(EnforcementOptions enforcementOptions);

}
